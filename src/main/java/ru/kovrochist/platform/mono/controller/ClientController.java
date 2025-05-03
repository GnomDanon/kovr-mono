package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.ClientApi;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.dto.client.ClientStatusDto;
import ru.kovrochist.platform.mono.exception.ResourceConflictException;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {
	@Override
	public ResponseEntity<ClientDto> create(ClientDto client) throws DoesNotExistException {
		return null;
	}

	@Override
	public ResponseEntity<ClientDto> update(ClientDto client) throws DoesNotExistException {
		return null;
	}

	@Override
	public ResponseEntity<List<ClientDto>> get(String name, String phone, UUID statusId) {
		return null;
	}

	@Override
	public ResponseEntity<ClientDto> get(UUID id) throws DoesNotExistException {
		return null;
	}

	@Override
	public ResponseEntity<ClientStatusDto> createStatus(String status) throws ResourceConflictException {
		return null;
	}

	@Override
	public ResponseEntity<List<ClientStatusDto>> getStatuses() {
		return null;
	}

	@Override
	public ResponseEntity<ClientStatusDto> getStatus(UUID id) throws DoesNotExistException {
		return null;
	}
}
