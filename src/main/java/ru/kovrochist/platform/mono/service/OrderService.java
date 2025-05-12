package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.dto.employee.AssignedEmployeeDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.UpdateOrderItemDto;
import ru.kovrochist.platform.mono.entity.AssignedEmployees;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.entity.OrderItems;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.exception.order.OrderDoesNotExistException;
import ru.kovrochist.platform.mono.exception.order.OrderItemDoesNotExistsException;
import ru.kovrochist.platform.mono.filter.OrderFilter;
import ru.kovrochist.platform.mono.mapper.employee.EmployeeMapper;
import ru.kovrochist.platform.mono.mapper.order.OrderMapper;
import ru.kovrochist.platform.mono.repository.OrderRepository;
import ru.kovrochist.platform.mono.security.user.User;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;
import ru.kovrochist.platform.mono.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	private final ClientService clientService;
	private final EmployeeService employeeService;
	private final AssignedEmployeeService assignedEmployeeService;
	private final OrderItemService itemService;

	private final User USER;

	public List<OrderDto> fetchOrder() {
		List<Orders> orders = get();
		return orders.stream().map(OrderMapper::map).collect(Collectors.toList());
	}

	public List<OrderDto> getOrders(OrderFilter filter) {
		List<Orders> orders = get(filter);
		return orders.stream().map(OrderMapper::map).collect(Collectors.toList());
	}

	public OrderDto getOrderById(Long id) throws OrderDoesNotExistException {
		return OrderMapper.map(getById(id));
	}

	public List<OrderDto> getEmployeeOrders(Long employeeId) {
		List<Orders> orders = getByEmployeeId(employeeId);
		return orders.stream().map(OrderMapper::map).collect(Collectors.toList());
	}

	public List<OrderDto> getClientOrders(Long clientId) {
		List<Orders> orders = getByClientId(clientId);
		return orders.stream().map(OrderMapper::map).collect(Collectors.toList());
	}

	public OrderDto updateOrderStatus(Long orderId, String status) throws DoesNotExistException {
		return OrderMapper.map(updateStatus(orderId, status));
	}

	public OrderDto createOrder(OrderDto order) throws DoesNotExistException {
		DeliveryType deliveryType = DeliveryType.byLabel(order.getDeliveryType());
		String deliveryDays = order.getDeliveryDays() == null ? null : OrderMapper.getDeliveryDays(order.getDeliveryDays());
		String sources = order.getSources() == null ? null : String.join(StringUtil.SEPARATOR, order.getSources());
		OrderStatus status = order.getStatus() == null ? OrderStatus.CREATED : OrderStatus.byLabel(order.getStatus());
		ClientDto client = order.getClient();
		Orders newOrder = create(client.getFirstName(), client.getFirstName(), order.getPhone(), order.getCity(), order.getAddress(), order.getDistrict(), order.getComment(), deliveryType, deliveryDays, order.getDeliveryTimeStart(), order.getDeliveryTimeEnd(), order.getDiscount(), sources, order.getPrice(), status);
		List<OrderItems> items = itemService.update(newOrder, order.getItems());
		newOrder = assignEmployeeToOrder(newOrder, USER.getId());
		return OrderMapper.map(newOrder.setItems(items));
	}

	public OrderDto updateOrderItemServices(Long orderId, UpdateOrderItemDto updateInfo) throws OrderDoesNotExistException, OrderItemDoesNotExistsException {
		itemService.updateServices(updateInfo.getItemId(), updateInfo.getServices());
		Orders order = getById(orderId);
		return OrderMapper.map(update(order));
	}

	public OrderDto update(OrderDto updateInfo) throws DoesNotExistException {
		Orders order = update(updateInfo.getId(), updateInfo.getStatus(), updateInfo.getComment(), updateInfo.getDeliveryType(), updateInfo.getPhone(), updateInfo.getCity(), updateInfo.getAddress(), updateInfo.getDistrict(), updateInfo.getDeliveryDays() == null ? null : OrderMapper.getDeliveryDays(updateInfo.getDeliveryDays()), updateInfo.getDeliveryTimeStart(), updateInfo.getDeliveryTimeEnd(), updateInfo.getDiscount(), updateInfo.getPrice(), updateInfo.getSources() == null ? null : String.join(StringUtil.SEPARATOR, updateInfo.getSources()));
		order = assignEmployeeToOrder(order, USER.getId());
		List<OrderItems> items = itemService.update(order, updateInfo.getItems());
		return OrderMapper.map(order.setItems(items));
	}

	public OrderDto assignEmployeeToOrder(Long orderId, Long employeeId) throws OrderDoesNotExistException, EmployeeDoesNotExistException {
		Orders order = getById(orderId);
		Employees employee = employeeService.getById(employeeId);
		List<AssignedEmployees> assignedEmployee = assignedEmployeeService.create(order, employee);
		order.setEmployees(assignedEmployee);
		return OrderMapper.map(update(order));
	}

	public Orders assignEmployeeToOrder(Orders order, Long employeeId) throws EmployeeDoesNotExistException {
		Employees employee = employeeService.getById(employeeId);
		List<AssignedEmployees> assignedEmployee = assignedEmployeeService.create(order, employee);
		order.setEmployees(assignedEmployee);
		return order;
	}

	public void deAssignEmployee(Long orderId, Long employeeId) {
		assignedEmployeeService.remove(orderId, employeeId);
	}

	public AssignedEmployeeDto updateEmployeeComment(Long orderId, Long employeeId, String comment) throws DoesNotExistException {
		Orders order = getById(orderId);
		AssignedEmployees assignedEmployee = assignedEmployeeService.updateComment(orderId, employeeId, comment);
		update(order);
		return EmployeeMapper.mapAssigned(assignedEmployee);
	}

	public OrderDto rescheduleOrder(Long orderId, String[] deliveryDays, Date deliveryTimeStart, Date deliveryTimeEnd) throws DoesNotExistException {
		return OrderMapper.map(updateDelivery(orderId, OrderMapper.getDeliveryDays(deliveryDays), deliveryTimeStart, deliveryTimeEnd));
	}

	public List<Orders> get() {
		List<Orders> result = new ArrayList<>();
		Iterable<Orders> orders = orderRepository.findAll();

		for (Orders order : orders) {
			result.add(order);
		}

		return result;
	}

	public List<Orders> get(String filter) {
		List<Orders> result = new ArrayList<>();
		Iterable<Orders> orders = orderRepository.find(filter);

		for (Orders order : orders) {
			result.add(order);
		}

		return result;
	}

	public List<Orders> get(OrderFilter filter) {
		List<Orders> result = new ArrayList<>();
		Iterable<Orders> orders = orderRepository.find(filter.getSearch(), filter.getStatus(), filter.getDeliveryType(), filter.getDistrict());

		for (Orders order : orders) {
			result.add(order);
		}

		return result;
	}

	public Orders getById(Long id) throws OrderDoesNotExistException {
		return orderRepository.findById(id).orElseThrow(() -> new OrderDoesNotExistException(id));
	}

	public List<Orders> getByEmployeeId(Long employeeId) {
		return assignedEmployeeService.getOrdersByEmployeeId(employeeId);
	}

	public List<Orders> getByClientId(Long clientId) {
		List<Orders> result = new ArrayList<>();
		Iterable<Orders> orders = orderRepository.findByClientId(clientId);

		for (Orders order : orders) {
			result.add(order);
		}

		return result;
	}

	public Orders create(String firstName, String lastName, String phone, String city, String address, String district, String comment, DeliveryType deliveryType, String deliveryDays, Date deliverTimeStart, Date deliveryTimeEnd, Double discount, String sources, Integer price, OrderStatus status) {
		Clients client = clientService.getByPhone(phone);

		if (client == null) {
			client = clientService.create(phone, firstName, lastName, city, address);
		}

		Orders order = new Orders()
				.setClient(client)
				.setPhone(phone)
				.setCity(city)
				.setAddress(address)
				.setDistrict(district)
				.setComment(comment)
				.setDeliveryType(deliveryType)
				.setDeliveryDays(deliveryDays)
				.setDeliveryTimeStart(deliverTimeStart)
				.setDeliveryTimeEnd(deliveryTimeEnd)
				.setDiscount(discount)
				.setPrice(price)
				.setSources(sources)
				.setStatus(status);

		return create(order);
	}

	public Orders create(Clients client, String phone, String city, String address, String comment) {
		Orders order = new Orders()
				.setClient(client)
				.setPhone(phone)
				.setCity(city)
				.setAddress(address)
				.setComment(comment)
				.setStatus(OrderStatus.CREATED);

		return create(order);
	}

	public Orders create(Orders order) {
		Date now = new Date();
		return orderRepository.save(order.setCreatedAt(now).setUpdatedAt(now));
	}

	public Orders update(Long id, String status, String comment, String deliveryType, String phone, String city, String address, String district, String deliveryDays, Date deliveryTimeStart, Date deliveryTimeEnd, Double discount, Integer price, String sources) throws DoesNotExistException {
		Orders order = getById(id);
		return update(order.setStatus(OrderStatus.byLabel(status)).setComment(comment).setDeliveryType(DeliveryType.byLabel(deliveryType)).setPhone(phone).setCity(city).setAddress(address).setDistrict(district).setDeliveryDays(deliveryDays).setDeliveryTimeStart(deliveryTimeStart).setDeliveryTimeEnd(deliveryTimeEnd).setDiscount(discount).setPrice(price).setSources(sources));
	}

	public Orders updateStatus(Long id, String statusLabel) throws DoesNotExistException {
		Orders order = getById(id);
		OrderStatus status = OrderStatus.byLabel(statusLabel);
		return update(order.setStatus(status));
	}

	public Orders updateDelivery(Long id, String deliveryDays, Date deliveryTimeStart, Date deliveryTimeEnt) throws OrderDoesNotExistException {
		Orders order = getById(id);
		return update(order.setDeliveryDays(deliveryDays).setDeliveryTimeStart(deliveryTimeStart).setDeliveryTimeEnd(deliveryTimeEnt));
	}

	public Orders update(Orders order) {
		return orderRepository.save(order.setUpdatedAt(new Date()));
	}

	public void remove(Long id) {
		orderRepository.deleteById(id);
	}
}
