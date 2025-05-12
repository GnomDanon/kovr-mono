package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;

import java.util.List;
import java.util.Map;

@RequestMapping("/clients")
@Tag(name = "Клиент")
public interface ClientApi {

	@Operation(summary = "Получение клиентов")
	@GetMapping
	ResponseEntity<List<ClientDto>> getClients();

	@Operation(summary = "Получение клиента")
	@GetMapping("/{id}")
	ResponseEntity<ClientDto> getClientById(@PathVariable Long id) throws ClientDoesNotExistException;

	@Operation(summary = "Получение отфильтрованных клиентов")
	@GetMapping("/filter")
	ResponseEntity<List<ClientDto>> fetchFilteredClients(@RequestParam Map<String, String> allParams);

	@Operation(summary = "Получение отфильтрованных клиентов")
	@GetMapping("/search")
	ResponseEntity<List<ClientDto>> searchClients(@RequestParam String query);

	@Operation(summary = "Обновление профиля клиента")
	@PatchMapping("/{clientId}")
	ResponseEntity<ClientDto> updateClientInfo(@PathVariable Long clientId, @RequestBody ClientDto client) throws DoesNotExistException;
}
