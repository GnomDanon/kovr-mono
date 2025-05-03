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
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.dto.client.ClientStatusDto;
import ru.kovrochist.platform.mono.exception.ResourceConflictException;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.List;
import java.util.UUID;

@RequestMapping("/client")
@Tag(name = "Клиент")
public interface ClientApi {

	@Operation(summary = "Регистрация нового клиента в системе")
	@PostMapping
	ResponseEntity<ClientDto> create(@RequestBody ClientDto client) throws DoesNotExistException;

	@Operation(summary = "Обновление информации о клиенте")
	@PutMapping
	ResponseEntity<ClientDto> update(@RequestBody ClientDto client) throws DoesNotExistException;

	@Operation(summary = "Получение информации о клиентах")
	@GetMapping
	ResponseEntity<List<ClientDto>> get(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "phone", required = false) String phone,
			@RequestParam(name = "status", required = false) Long statusId
	);

	@Operation(summary = "Получение информации о клиенте")
	@GetMapping("/{id}")
	ResponseEntity<ClientDto> get(@PathVariable Long id) throws DoesNotExistException;

	@Operation(summary = "Создание нового статуса клиента")
	@PostMapping("/status")
	ResponseEntity<ClientStatusDto> createStatus(@RequestBody String status) throws ResourceConflictException;

	@Operation(summary = "Получение статусов клиентов")
	@GetMapping("/status")
	ResponseEntity<List<ClientStatusDto>> getStatuses();

	@Operation(summary = "Получение статуса клиента по идентификатору")
	@GetMapping("/status/{id}")
	ResponseEntity<ClientStatusDto> getStatus(@PathVariable Long id) throws DoesNotExistException;
}
