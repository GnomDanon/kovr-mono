package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.ClientApi;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.ResourceAccessException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.filter.ClientFilter;
import ru.kovrochist.platform.mono.service.ClientService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {

	private final ClientService clientService;

	@Override
	public ResponseEntity<List<ClientDto>> getClients() {
		return ResponseEntity.ok(clientService.getClients());
	}

	@Override
	public ResponseEntity<ClientDto> getClientById(Long id) throws ClientDoesNotExistException, ResourceAccessException {
		return ResponseEntity.ok(clientService.getClient(id));
	}

	@Override
	public ResponseEntity<List<ClientDto>> fetchFilteredClients(Map<String, String> allParams) {
		return ResponseEntity.ok(clientService.getClients(new ClientFilter(allParams)));
	}

	@Override
	public ResponseEntity<List<ClientDto>> searchClients(String query) {
		return ResponseEntity.ok(clientService.getClients(query));
	}

	@Override
	public ResponseEntity<ClientDto> updateClientInfo(Long clientId, ClientDto client) throws DoesNotExistException, ResourceAccessException {
		return ResponseEntity.ok(clientService.update(clientId, client));
	}
}
