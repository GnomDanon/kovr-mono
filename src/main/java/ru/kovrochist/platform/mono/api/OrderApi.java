package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.order.CreateOrderDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.UpdateOrderDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.List;
import java.util.UUID;

@RequestMapping("/order")
@Tag(name = "Заказ")
public interface OrderApi {

	@Operation(summary = "Регистрация нового заказа в системе")
	@PostMapping("/create")
	ResponseEntity<OrderDto> create(@RequestBody CreateOrderDto orderDto) throws DoesNotExistException;

	@Operation(summary = "Обновление информации о заказе")
	@PutMapping("/update")
	ResponseEntity<OrderDto> update(@RequestBody UpdateOrderDto orderDto) throws DoesNotExistException ;

	@Operation(summary = "Смена исполнителя")
	@GetMapping("/change-executor")
	ResponseEntity<OrderDto> changeExecutor(@RequestParam UUID orderId, @RequestParam UUID employeeId) throws DoesNotExistException;

	@Operation(summary = "Получение информации о заказах")
	@GetMapping("/get")
	ResponseEntity<List<OrderDto>> get();

	@Operation(summary = "Получение информации о заказе по идентификатору")
	@GetMapping("/get/{id}")
	ResponseEntity<OrderDto> get(@PathVariable UUID id) throws DoesNotExistException;

	@Operation(summary = "Получение информации о заказе по идентификатору сотрудника")
	@GetMapping("/get/byEmployee")
	ResponseEntity<List<OrderDto>> getByEmployeeId(@RequestParam UUID employeeId);

	@Operation(summary = "Получение информации о заказе по идентификатору клиента")
	@GetMapping("/get/byClient")
	ResponseEntity<List<OrderDto>> getByClientId(@RequestParam UUID clientId);
}
