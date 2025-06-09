package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.OrderApi;
import ru.kovrochist.platform.mono.dto.employee.AssignedEmployeeDto;
import ru.kovrochist.platform.mono.dto.order.AssignDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.RescheduleDto;
import ru.kovrochist.platform.mono.dto.order.StatusWrapper;
import ru.kovrochist.platform.mono.dto.order.UpdateCommentDto;
import ru.kovrochist.platform.mono.dto.order.UpdateOrderItemDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.ResourceAccessException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.exception.order.OrderDoesNotExistException;
import ru.kovrochist.platform.mono.exception.order.OrderItemDoesNotExistsException;
import ru.kovrochist.platform.mono.filter.OrderFilter;
import ru.kovrochist.platform.mono.service.OrderService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

	private final OrderService orderService;

	@Override
	public ResponseEntity<List<OrderDto>> fetchOrders() {
		return ResponseEntity.ok(orderService.fetchOrder());
	}

	@Override
	public ResponseEntity<List<OrderDto>> fetchFilteredOrders(Map<String, String> allParams) {
		return ResponseEntity.ok(orderService.getOrders(new OrderFilter(allParams)));
	}

	@Override
	public ResponseEntity<OrderDto> getOrderById(Long id) throws OrderDoesNotExistException, ResourceAccessException {
		return ResponseEntity.ok(orderService.getOrderById(id));
	}

	@Override
	public ResponseEntity<List<OrderDto>> getEmployeeOrders(Long employeeId) {
		return ResponseEntity.ok(orderService.getEmployeeOrders(employeeId));
	}

	@Override
	public ResponseEntity<List<OrderDto>> getClientOrders(Long clientId) throws ResourceAccessException {
		return ResponseEntity.ok(orderService.getClientOrders(clientId));
	}

	@Override
	public ResponseEntity<OrderDto> updateOrderStatus(Long orderId, StatusWrapper status) throws DoesNotExistException, ResourceAccessException {
		return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status.getStatus()));
	}

	@Override
	public ResponseEntity<OrderDto> createOrder(OrderDto order) throws DoesNotExistException {
		return ResponseEntity.ok(orderService.createOrder(order));
	}

	@Override
	public ResponseEntity<String> deleteOrder(Long id) {
		orderService.remove(id);
		return ResponseEntity.ok("ok");
	}

	@Override
	public ResponseEntity<OrderDto> update(OrderDto orderDto) throws DoesNotExistException, ResourceAccessException {
		return ResponseEntity.ok(orderService.update(orderDto));
	}

	@Override
	public ResponseEntity<OrderDto> updateOrderItemServices(Long orderId, UpdateOrderItemDto updateDto) throws OrderItemDoesNotExistsException, OrderDoesNotExistException, ResourceAccessException {
		return ResponseEntity.ok(orderService.updateOrderItemServices(orderId, updateDto));
	}

	@Override
	public ResponseEntity<OrderDto> assignEmployeeToOrder(Long orderId, AssignDto assignDto) throws EmployeeDoesNotExistException, OrderDoesNotExistException {
		return ResponseEntity.ok(orderService.assignEmployeeToOrder(orderId, assignDto.getEmployeeId()));
	}

	@Override
	public ResponseEntity<String> deAssignEmployee(Long orderId, Long employeeId)  {
		orderService.deAssignEmployee(orderId, employeeId);
		return ResponseEntity.ok("ok");
	}

	@Override
	public ResponseEntity<AssignedEmployeeDto> updateEmployeeComment(Long orderId, UpdateCommentDto updateDto) throws DoesNotExistException, ResourceAccessException {
		return ResponseEntity.ok(orderService.updateEmployeeComment(orderId, updateDto.getEmployeeId(), updateDto.getComment()));
	}

	@Override
	public ResponseEntity<OrderDto> rescheduleOrder(Long orderId, RescheduleDto rescheduleDto) throws DoesNotExistException, ResourceAccessException {
		return ResponseEntity.ok(orderService.rescheduleOrder(orderId, rescheduleDto.getDeliveryDays(), rescheduleDto.getDeliveryTimeStart(), rescheduleDto.getDeliveryTimeEnd()));
	}
}
