package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.EmployeeOrderItems;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.ResourceConflictException;
import ru.kovrochist.platform.mono.exception.order.CannotRejectException;
import ru.kovrochist.platform.mono.exception.order.OrderDoesNotExistException;
import ru.kovrochist.platform.mono.repository.OrderRepository;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	private final EmployeeService employeeService;
	private final EmployeeOrderItemService employeeOrderItemService;

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

	public Orders getById(UUID id) throws OrderDoesNotExistException {
		return orderRepository.findById(id).orElseThrow(() -> new OrderDoesNotExistException(id));
	}

	public List<Orders> getByEmployeeId(UUID employeeId) {
		return employeeOrderItemService.getOrdersByEmployeeId(employeeId);
	}

	public Orders create(Clients client, String city, String address, String comment) {
		Date now = new Date();

		Orders order = new Orders()
				.setClient(client)
				.setCity(city)
				.setAddress(address)
				.setComment(comment)
				.setCreatedAt(now)
				.setUpdatedAt(now)
				.setApproved(false)
				.setStatus(OrderStatus.CREATED);

		return orderRepository.save(order);
	}

	public Orders update(Orders order) {
		return orderRepository.save(order.setUpdatedAt(new Date()));
	}

	public Orders update(UUID id, String city, String address, String comment) throws OrderDoesNotExistException {
		Orders order = getById(id);
		return update(order.setCity(city).setAddress(address).setComment(comment));
	}

	public Orders updateStatus(UUID id) throws OrderDoesNotExistException {
		Orders order = getById(id);
		OrderStatus nextStatus = order.getStatus().getNextStatus();

		if (nextStatus == OrderStatus.DELIVERING) {
			order.setDeliveryTimeStart(new Date());
		} else if (nextStatus == OrderStatus.COMPLETED) {
			order.setDeliveryTimeEnd(new Date());
		}

		return update(order.setStatus(nextStatus));
	}

	public Orders addEmployee(UUID orderId, UUID employeeId) throws DoesNotExistException {
		Orders order = getById(orderId);
		Employees employee = employeeService.getById(employeeId);
		employeeOrderItemService.create(order, employee);
		return update(order);
	}

	public Orders reject(UUID id) throws DoesNotExistException, ResourceConflictException {
		Orders order = getById(id);
		OrderStatus status = order.getStatus();

		if (!status.canBeRejected()) {
			throw new CannotRejectException(id, status);
		}

		orderRepository.delete(order);
		return order;
	}
}
