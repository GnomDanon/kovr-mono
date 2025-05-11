package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.List;

@RequestMapping("/client")
@Tag(name = "Клиент")
public interface ClientApi {

	@Operation(summary = "Получение клиентов")
	@GetMapping
	ResponseEntity<List<ClientDto>> getClients();

	@Operation(summary = "Получение отфильтрованных клиентов")
	@GetMapping("/search")
	ResponseEntity<List<ClientDto>> searchClients(@RequestParam String query);

	@Operation(summary = "Обновление профиля клиента")
	@GetMapping("/{clientId}")
	ResponseEntity<ClientDto> updateClientInfo(@PathVariable Long clientId, @RequestBody ClientDto client) throws DoesNotExistException;
}
