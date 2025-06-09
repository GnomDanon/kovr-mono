package ru.kovrochist.platform.mono.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.OrderItemDto;
import ru.kovrochist.platform.mono.entity.*;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.ResourceAccessException;
import ru.kovrochist.platform.mono.exception.order.OrderDoesNotExistException;
import ru.kovrochist.platform.mono.filter.OrderFilter;
import ru.kovrochist.platform.mono.repository.OrderRepository;
import ru.kovrochist.platform.mono.security.access.AccessFilter;
import ru.kovrochist.platform.mono.security.user.User;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderServiceTest {

	@Mock private OrderRepository orderRepository;
	@Mock private ClientService clientService;
	@Mock private EmployeeService employeeService;
	@Mock private AssignedEmployeeService assignedEmployeeService;
	@Mock private OrderItemService itemService;
	@Mock private AccessFilter accessFilter;
	@Mock private User user;

	@InjectMocks
	private OrderService orderService;

	private final Long testOrderId = 1L;
	private final Long testEmployeeId = 2L;
	private final Long testClientId = 3L;
	private final String testPhone = "79998887766";
	private Orders testOrder;
	private OrderDto testOrderDto;

	@BeforeEach
	void setUp() {
		testOrder = new Orders()
				.setId(testOrderId)
				.setStatus(OrderStatus.CREATED)
				.setClient(new Clients().setId(testClientId));
		List<AssignedEmployees> employees = new ArrayList<>();
		employees.add(new AssignedEmployees().setId(1L).setOrder(testOrder).setEmployee(new Employees().setId(2L)));
		testOrder.setEmployees(employees);

		testOrderDto = new OrderDto()
				.setId(testOrderId)
				.setStatus("Одобрено")
				.setPhone(testPhone)
				.setItems(new OrderItemDto[] {})
				.setClient((ClientDto) new ClientDto().setId(testClientId));

		when(user.getId()).thenReturn(testEmployeeId);
	}

	@Test
	@DisplayName("Получение заказов")
	void fetchOrder_ShouldReturnListOfOrderDtos() {
		when(orderRepository.findAll()).thenReturn(Collections.singletonList(testOrder));

		List<OrderDto> result = orderService.fetchOrder();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(testOrderId, result.get(0).getId());
	}

	@Test
	@DisplayName("Получение отфильтрованных заказов")
	void getOrders_WithFilter_ShouldReturnFilteredList() {
		HashMap<String, String> filterSearch = new HashMap<>();
		filterSearch.put(OrderFilter.SEARCH, "search");
		filterSearch.put(OrderFilter.STATUS, "CREATED");
		filterSearch.put(OrderFilter.DELIVERY_TYPE, "COURIER");
		filterSearch.put(OrderFilter.DISTRICT, "district");
		OrderFilter filter = new OrderFilter(filterSearch);
		when(orderRepository.find(filter.getSearch(), filter.getStatus(), filter.getDeliveryType(), filter.getDistrict()))
				.thenReturn(Collections.singletonList(testOrder));

		List<OrderDto> result = orderService.getOrders(filter);

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	@DisplayName("Получение заказа по идентификатору")
	void getOrderById_WithNonExistingId_ShouldThrowException() {
		when(orderRepository.findById(testOrderId)).thenReturn(Optional.empty());

		assertThrows(OrderDoesNotExistException.class, () -> orderService.getOrderById(testOrderId));
	}

	@Test
	@DisplayName("Получение заказов сотрудника")
	void getEmployeeOrders_ShouldReturnEmployeeOrders() {
		when(assignedEmployeeService.getOrdersByEmployeeId(testEmployeeId))
				.thenReturn(Collections.singletonList(testOrder));

		List<OrderDto> result = orderService.getEmployeeOrders(testEmployeeId);

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	@DisplayName("Получение заказов клиента")
	void getClientOrders_ShouldReturnClientOrders() throws ResourceAccessException {
		when(orderRepository.findByClientId(testClientId))
				.thenReturn(Collections.singletonList(testOrder));

		List<OrderDto> result = orderService.getClientOrders(testClientId);

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	@DisplayName("Обновление статуса заказа с невалидным доступом")
	void updateOrderStatus_WithInvalidAccess_ShouldThrowException() throws Exception {
		when(orderRepository.findById(testOrderId)).thenReturn(Optional.of(testOrder));
		doThrow(ResourceAccessException.class).when(accessFilter).operatorOrAdminOrAssignee(testOrder);

		assertThrows(ResourceAccessException.class,
				() -> orderService.updateOrderStatus(testOrderId, "PROCESSING"));
	}

	@Test
	@DisplayName("Создание заказа")
	void createOrder_ShouldCreateNewOrder() throws DoesNotExistException {
		when(clientService.getByPhone(testPhone)).thenReturn(null);
		when(clientService.create(any(), any(), any(), any(), any()))
				.thenReturn(new Clients().setPhone(testPhone));
		when(orderRepository.save(any())).thenReturn(testOrder);
		when(itemService.update(any(), any())).thenReturn(new ArrayList<>());
		when(employeeService.getById(testEmployeeId)).thenReturn(new Employees());
		when(assignedEmployeeService.create(any(), any())).thenReturn(new ArrayList<>());

		OrderDto result = orderService.createOrder(testOrderDto);

		assertNotNull(result);
		verify(orderRepository).save(any());
	}

	@Test
	@DisplayName("Обновление заказа")
	void update_ShouldUpdateOrder() throws DoesNotExistException, ResourceAccessException {
		when(orderRepository.findById(testOrderId)).thenReturn(Optional.of(testOrder));
		when(orderRepository.save(any())).thenReturn(testOrder);
		when(itemService.update(any(), any())).thenReturn(new ArrayList<>());
		when(employeeService.getById(testEmployeeId)).thenReturn(new Employees());
		when(assignedEmployeeService.create(any(), any())).thenReturn(new ArrayList<>());

		OrderDto result = orderService.update(testOrderDto);

		assertNotNull(result);
		verify(orderRepository).save(any());
	}

	@Test
	@DisplayName("Назначение сотрудника на заказ")
	void assignEmployeeToOrder_ShouldAssignEmployee() throws Exception {
		when(orderRepository.findById(testOrderId)).thenReturn(Optional.of(testOrder));
		when(employeeService.getById(testEmployeeId)).thenReturn(new Employees());
		when(assignedEmployeeService.create(any(), any())).thenReturn(new ArrayList<>());
		when(orderRepository.save(any())).thenReturn(testOrder);

		OrderDto result = orderService.assignEmployeeToOrder(testOrderId, testEmployeeId);

		assertNotNull(result);
		verify(assignedEmployeeService).create(any(), any());
	}

	@Test
	@DisplayName("Снятие сотрудника с заказа")
	void deAssignEmployee_ShouldRemoveAssignment() {
		assertDoesNotThrow(() -> orderService.deAssignEmployee(testOrderId, testEmployeeId));
		verify(assignedEmployeeService).remove(testOrderId, testEmployeeId);
	}

	@Test
	@DisplayName("Удаление заказа")
	void remove_ShouldDeleteOrder() {
		assertDoesNotThrow(() -> orderService.remove(testOrderId));
		verify(orderRepository).deleteById(testOrderId);
	}
}
