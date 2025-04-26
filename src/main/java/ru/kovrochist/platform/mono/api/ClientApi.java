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
import ru.kovrochist.platform.mono.dto.client.CreateClientDto;
import ru.kovrochist.platform.mono.dto.client.UpdateClientDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.List;
import java.util.UUID;

@RequestMapping("/client")
@Tag(name = "Клиент")
public interface ClientApi {

	@Operation(summary = "Регистрация нового клиента в системе")
	@PostMapping("/create")
	ResponseEntity<ClientDto> create(@RequestBody CreateClientDto client) throws DoesNotExistException;

	@Operation(summary = "Обновление информации о клиенте")
	@PutMapping("/update")
	ResponseEntity<ClientDto> update(@RequestBody UpdateClientDto client) throws DoesNotExistException;

	@Operation(summary = "Получение информации о клиенте")
	@GetMapping("/get/{id}")
	ResponseEntity<ClientDto> get(@PathVariable UUID id) throws DoesNotExistException;

	@Operation(summary = "Получение информации о клиентах по статусу")
	@GetMapping("/get/byStatus")
	ResponseEntity<List<ClientDto>> getByStatusId(@RequestParam UUID status);

	@Operation(summary = "Получение информации о клиентах")
	@GetMapping("/get")
	ResponseEntity<List<ClientDto>> get();
}
