package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.request.RequestDto;
import ru.kovrochist.platform.mono.exception.ResourceAccessException;

@RequestMapping(RequestApi.REQUEST_ENTRY_POINT)
@Tag(name = "Заявка")
public interface RequestApi {

	String REQUEST_ENTRY_POINT = "/request";

	@Operation(summary = "Регистрация новой заявки")
	@PostMapping
	ResponseEntity<OrderDto> create(@RequestBody RequestDto request) throws ResourceAccessException;
}
