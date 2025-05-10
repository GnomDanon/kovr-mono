package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.ClientApi;
import ru.kovrochist.platform.mono.dto.client.ClientDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {

	@Override
	public ResponseEntity<List<ClientDto>> getClients() {
		return null;
	}

	@Override
	public ResponseEntity<List<ClientDto>> searchClients(String query) {
		return null;
	}

	@Override
	public ResponseEntity<ClientDto> updateClientInfo(Long clientId, ClientDto client) {
		return null;
	}
}
