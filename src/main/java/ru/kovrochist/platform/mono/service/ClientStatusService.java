package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.entity.ClientStatuses;
import ru.kovrochist.platform.mono.exception.client.ClientStatusAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.client.ClientStatusDoesNotExistException;
import ru.kovrochist.platform.mono.repository.ClientStatusRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientStatusService {

	private final ClientStatusRepository clientStatusRepository;

	public ClientStatuses create(String name) throws ClientStatusAlreadyExistsException {
		ClientStatuses status = clientStatusRepository.findByName(name).orElse(null);

		if (status != null)
			throw new ClientStatusAlreadyExistsException(name);

		return clientStatusRepository.save(new ClientStatuses().setName(name));
	}

	public List<ClientStatuses> get() {
		List<ClientStatuses> result = new ArrayList<>();
		Iterable<ClientStatuses> statuses = clientStatusRepository.findAll();

		for (ClientStatuses status : statuses) {
			result.add(status);
		}

		return result;
	}

	public ClientStatuses getById(UUID id) throws ClientStatusDoesNotExistException {
		return clientStatusRepository.findById(id).orElseThrow(() -> new ClientStatusDoesNotExistException(id));
	}
}
