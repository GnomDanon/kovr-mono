package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.ClientApi;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.dto.client.CreateClientDto;
import ru.kovrochist.platform.mono.dto.client.UpdateClientDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.service.ClientService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {

	private final ClientService clientService;

	@Override
	public ResponseEntity<ClientDto> create(CreateClientDto client) throws DoesNotExistException {
		return ResponseEntity.ok(clientService.create(client));
	}

	@Override
	public ResponseEntity<ClientDto> update(UpdateClientDto client) throws DoesNotExistException {
		return ResponseEntity.ok(clientService.update(client));
	}

	@Override
	public ResponseEntity<ClientDto> get(UUID id) throws DoesNotExistException {
		return ResponseEntity.ok(clientService.getById(id));
	}

	@Override
	public ResponseEntity<List<ClientDto>> get() {
		return ResponseEntity.ok(clientService.get());
	}

	@Override
	public ResponseEntity<List<ClientDto>> getByStatusId(UUID status) {
		return ResponseEntity.ok(clientService.getByClientStatusId(status));
	}
}
