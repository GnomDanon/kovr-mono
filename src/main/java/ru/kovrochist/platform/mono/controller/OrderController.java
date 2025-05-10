package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.OrderApi;
import ru.kovrochist.platform.mono.dto.metadata.TypeWrapper;
import ru.kovrochist.platform.mono.dto.order.AssignEmployeeDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.RescheduleDto;
import ru.kovrochist.platform.mono.dto.order.UpdateCommentDto;
import ru.kovrochist.platform.mono.dto.order.UpdateOrderItemDto;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

	@Override
	public ResponseEntity<List<OrderDto>> fetchOrders() {
		return null;
	}

	@Override
	public ResponseEntity<List<OrderDto>> fetchFilteredOrders(Map<String, String> allParams) {
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> updateOrderStatus(Long orderId, TypeWrapper type) {
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> getOrderDto(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<String> deleteOrder(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> updateOrderItemServices(Long orderId, UpdateOrderItemDto updateDto) {
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> assignEmployeeToOrder(Long orderId, AssignEmployeeDto assignDto) {
		return null;
	}

	@Override
	public ResponseEntity<String> deAssignEmployee(Long orderId, Long employeeId) {
		return null;
	}

	@Override
	public ResponseEntity<AssignEmployeeDto> updateEmployeeComment(Long orderId, UpdateCommentDto updateDto) {
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> rescheduleOrder(Long orderId, RescheduleDto rescheduleDto) {
		return null;
	}

	@Override
	public ResponseEntity<List<OrderDto>> getClientOrders(Long clientId) {
		return null;
	}
}
