package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.OrderApi;
import ru.kovrochist.platform.mono.dto.order.CreateOrderDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.UpdateOrderDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

	private final OrderService orderService;

	@Override
	public ResponseEntity<OrderDto> create(CreateOrderDto orderDto) throws DoesNotExistException {
		return ResponseEntity.ok(orderService.create(orderDto));
	}

	@Override
	public ResponseEntity<OrderDto> update(UpdateOrderDto orderDto) throws DoesNotExistException {
		return ResponseEntity.ok(orderService.update(orderDto));
	}

	@Override
	public ResponseEntity<OrderDto> changeExecutor(UUID orderId, UUID employeeId) throws DoesNotExistException {
		return ResponseEntity.ok(orderService.changeExecutor(orderId, employeeId));
	}

	@Override
	public ResponseEntity<List<OrderDto>> get() {
		return ResponseEntity.ok(orderService.get());
	}

	@Override
	public ResponseEntity<OrderDto> get(UUID id) throws DoesNotExistException {
		return ResponseEntity.ok(orderService.get(id));
	}

	@Override
	public ResponseEntity<List<OrderDto>> getByEmployeeId(UUID employeeId) {
		return ResponseEntity.ok(orderService.getByEmployeeId(employeeId));
	}

	@Override
	public ResponseEntity<List<OrderDto>> getByClientId(UUID clientId) {
		return ResponseEntity.ok(orderService.getByClientId(clientId));
	}
}
