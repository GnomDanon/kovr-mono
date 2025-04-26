package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovrochist.platform.mono.dto.address.AddressDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.UUID;

@RequestMapping("/auth")
@Tag(name = "Авторизация")
public interface AddressApi {

	@Operation(summary = "Получение адреса по идентификатору")
	@GetMapping("/get/{id}")
	ResponseEntity<AddressDto> get(@PathVariable UUID id) throws DoesNotExistException;
}