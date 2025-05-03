package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.ResourceConflictException;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.List;
import java.util.UUID;

@RequestMapping("/order")
@Tag(name = "Заказ")
public interface OrderApi {

	@Operation(summary = "Регистрация нового заказа в системе")
	@PostMapping
	ResponseEntity<OrderDto> create(@RequestBody OrderDto order) throws DoesNotExistException;

	@Operation(summary = "Обновление информации о заказе")
	@PutMapping
	ResponseEntity<OrderDto> update(@RequestBody OrderDto order) throws DoesNotExistException ;

	@Operation(summary = "Получение информации о заказах")
	@GetMapping
	ResponseEntity<List<OrderDto>> get(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "status", required = false) OrderStatus status,
			@RequestParam(name = "employee", required = false) UUID employeeId,
			@RequestParam(name = "client", required = false) UUID clientId
	);

	@Operation(summary = "Получение информации о заказе по идентификатору")
	@GetMapping("/{id}")
	ResponseEntity<OrderDto> get(@PathVariable UUID id) throws DoesNotExistException;

	@Operation(summary = "Назначение заказа сотруднику")
	@PutMapping("/employee")
	ResponseEntity<OrderDto> addEmployee(UUID orderId, UUID employeeId) throws DoesNotExistException;

	@Operation(summary = "Отмена заказа")
	@DeleteMapping
	ResponseEntity<OrderDto> reject(UUID orderId) throws DoesNotExistException, ResourceConflictException;
}
