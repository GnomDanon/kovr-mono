package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.ClientApi;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {

	private final ClientService clientService;

	@Override
	public ResponseEntity<List<ClientDto>> getClients() {
		return ResponseEntity.ok(clientService.getClients());
	}

	@Override
	public ResponseEntity<List<ClientDto>> searchClients(String query) {
		return ResponseEntity.ok(clientService.getClients(query));
	}

	@Override
	public ResponseEntity<ClientDto> updateClientInfo(Long clientId, ClientDto client) throws DoesNotExistException {
		return ResponseEntity.ok(clientService.update(clientId, client));
	}
}
