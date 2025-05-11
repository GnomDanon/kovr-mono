package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.employee.AssignedEmployeeDto;
import ru.kovrochist.platform.mono.dto.metadata.TypeWrapper;
import ru.kovrochist.platform.mono.dto.order.AssignDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.RescheduleDto;
import ru.kovrochist.platform.mono.dto.order.StatusWrapper;
import ru.kovrochist.platform.mono.dto.order.UpdateCommentDto;
import ru.kovrochist.platform.mono.dto.order.UpdateOrderItemDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.exception.order.OrderDoesNotExistException;
import ru.kovrochist.platform.mono.exception.order.OrderItemDoesNotExistsException;

import java.util.List;
import java.util.Map;

@RequestMapping("/orders")
@Tag(name = "Заказ")
public interface OrderApi {

	@Operation(summary = "Получение заказов")
	@GetMapping()
	ResponseEntity<List<OrderDto>> fetchOrders();

	@Operation(summary = "Получение отфильтрованных заказов")
	@GetMapping("/filter")
	ResponseEntity<List<OrderDto>> fetchFilteredOrders(@RequestParam Map<String, String> allParams);

	@Operation(summary = "Получение заказа по идентификатору")
	@GetMapping("/{id}")
	ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) throws OrderDoesNotExistException;

	@Operation(summary = "Получение заказов сотрудника")
	@GetMapping("/employee/{employeeId}")
	ResponseEntity<List<OrderDto>> getEmployeeOrders(@PathVariable Long employeeId);

	@Operation(summary = "Получение заказов клиента")
	@GetMapping("/client/{clientId}")
	ResponseEntity<List<OrderDto>> getClientOrders(@PathVariable Long clientId);

	@Operation(summary = "Обновление статуса заказа")
	@PatchMapping("/{orderId}/status")
	ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long orderId, @RequestBody StatusWrapper status) throws DoesNotExistException;

	@Operation(summary = "Создание заказа")
	@PostMapping
	ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) throws DoesNotExistException;

	@Operation(summary = "Удаление заказа")
	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteOrder(@PathVariable Long id);

	@Operation(summary = "Обновление заказа")
	@PatchMapping
	ResponseEntity<OrderDto> update(@RequestBody OrderDto orderDto);

	@Operation(summary = "Обновление услуги")
	@PatchMapping("/{orderId}/services")
	ResponseEntity<OrderDto> updateOrderItemServices(@PathVariable Long orderId, @RequestBody UpdateOrderItemDto updateDto) throws OrderItemDoesNotExistsException, OrderDoesNotExistException;

	@Operation(summary = "Назначение сотрудника на заказ")
	@PostMapping("/{orderId}/assignees")
	ResponseEntity<OrderDto> assignEmployeeToOrder(@PathVariable Long orderId, @RequestBody AssignDto assignDto) throws EmployeeDoesNotExistException, OrderDoesNotExistException;

	@Operation(summary = "Снятие сотрудника с заказа")
	@DeleteMapping("/{orderId}/assignees/{employeeId}")
	ResponseEntity<String> deAssignEmployee(@PathVariable Long orderId, @PathVariable Long employeeId);

	@Operation(summary = "Обновление комментарий от сотрудника")
	@PatchMapping("/{orderId}/employee-comment")
	ResponseEntity<AssignedEmployeeDto> updateEmployeeComment(@PathVariable Long orderId, @RequestBody UpdateCommentDto updateDto) throws DoesNotExistException;

	@Operation(summary = "Обновление планируемой даты доставки")
	@PatchMapping("/{orderId}/schedule")
	ResponseEntity<OrderDto> rescheduleOrder(@PathVariable Long orderId, @RequestBody RescheduleDto rescheduleDto) throws OrderDoesNotExistException;
}
