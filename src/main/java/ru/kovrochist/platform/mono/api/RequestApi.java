package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovrochist.platform.mono.dto.request.RequestDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@RequestMapping("/request")
@Tag(name = "Заявка")
public interface RequestApi {

	@Operation(summary = "Регистрация новой заявки")
	@PostMapping
	ResponseEntity<RequestDto> create(@RequestBody RequestDto request);

	@Operation(summary = "Обновление информации о заявке")
	@PutMapping
	ResponseEntity<RequestDto> update(@RequestBody RequestDto request) throws DoesNotExistException;
}
