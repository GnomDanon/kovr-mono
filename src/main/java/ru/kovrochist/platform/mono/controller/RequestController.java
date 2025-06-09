package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.RequestApi;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.request.RequestDto;
import ru.kovrochist.platform.mono.service.RequestService;

@RestController
@RequiredArgsConstructor
public class RequestController implements RequestApi {

	private final RequestService requestService;

	@Override
	public ResponseEntity<OrderDto> create(RequestDto request) {
		return ResponseEntity.ok(requestService.create(request));
	}
}
