package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.ResourceAccessException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;

import java.util.List;
import java.util.Map;

@RequestMapping("/clients")
@Tag(name = "Клиент")
public interface ClientApi {

	@Operation(summary = "Получение клиентов")
	@GetMapping
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<ClientDto>> getClients();

	@Operation(summary = "Получение клиента")
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('CLIENT', 'OPERATOR', 'COURIER', 'MASTER', 'ADMIN')")
	ResponseEntity<ClientDto> getClientById(@PathVariable Long id) throws ClientDoesNotExistException, ResourceAccessException;

	@Operation(summary = "Получение отфильтрованных клиентов")
	@GetMapping("/filter")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<ClientDto>> fetchFilteredClients(@RequestParam Map<String, String> allParams);

	@Operation(summary = "Получение отфильтрованных клиентов")
	@GetMapping("/search")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<ClientDto>> searchClients(@RequestParam String query);

	@Operation(summary = "Обновление профиля клиента")
	@PatchMapping("/{clientId}")
	@PreAuthorize("hasAnyRole('CLIENT', 'OPERATOR', 'COURIER', 'MASTER', 'ADMIN')")
	ResponseEntity<ClientDto> updateClientInfo(@PathVariable Long clientId, @RequestBody ClientDto client) throws DoesNotExistException, ResourceAccessException;
}
