package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.OrderApi;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.ResourceConflictException;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {
	@Override
	public ResponseEntity<OrderDto> create(OrderDto order) throws DoesNotExistException {
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> update(OrderDto order) throws DoesNotExistException {
		return null;
	}

	@Override
	public ResponseEntity<List<OrderDto>> get(String name, OrderStatus status, Long employeeId, Long clientId) {
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> get(Long id) throws DoesNotExistException {
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> addEmployee(Long orderId, Long employeeId) throws DoesNotExistException {
		return null;
	}

	@Override
	public ResponseEntity<OrderDto> reject(Long orderId) throws DoesNotExistException, ResourceConflictException {
		return null;
	}
}
