package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/statistics")
@Tag(name = "Статистика")
public interface StatisticsApi {

	@Operation(summary = "Получение статистики по сотрудникам")
	@GetMapping("/employee")
	ResponseEntity<String> getEmployeeStatistics();

	@Operation(summary = "Получение статистики по заказам")
	@GetMapping("/order")
	ResponseEntity<String> getOrderStatistics();

	@Operation(summary = "Получение статистики по клиентам")
	@GetMapping("/client")
	ResponseEntity<String> getClientStatistics();
}
