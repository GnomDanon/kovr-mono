package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.StatisticsApi;
import ru.kovrochist.platform.mono.service.StatisticsService;

@RestController
@RequiredArgsConstructor
public class StatisticsController implements StatisticsApi {

	private final StatisticsService statisticsService;

	@Override
	public ResponseEntity<String> getEmployeeStatistics() {
		return ResponseEntity.ok(statisticsService.getEmployeeStatistics());
	}

	@Override
	public ResponseEntity<String> getOrderStatistics() {
		return ResponseEntity.ok(statisticsService.getOrderStatistics());
	}

	@Override
	public ResponseEntity<String> getClientStatistics() {
		return ResponseEntity.ok(statisticsService.getClientStatistics());
	}
}
