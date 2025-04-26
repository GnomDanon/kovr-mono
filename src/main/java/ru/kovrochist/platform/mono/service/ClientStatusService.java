package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.client.status.ClientStatusDto;
import ru.kovrochist.platform.mono.dto.client.status.CreateClientStatusDto;
import ru.kovrochist.platform.mono.entity.ClientStatus;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.client.ClientStatusDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.client.ClientMapper;
import ru.kovrochist.platform.mono.repository.ClientStatusRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientStatusService {

	private final ClientStatusRepository clientStatusRepository;

	public ClientStatusDto create(CreateClientStatusDto clientStatusDto) {
		ClientStatus clientStatus = clientStatusRepository.findByName(clientStatusDto.getName()).orElse(null);

		if (clientStatus != null) {
			return ClientMapper.map(clientStatus);
		}

		clientStatus = clientStatusRepository.save(ClientMapper.map(clientStatusDto));
		return ClientMapper.map(clientStatus);
	}

	public ClientStatusDto getById(UUID id) throws DoesNotExistException {
		ClientStatus clientStatus = clientStatusRepository.findById(id).orElseThrow(() -> new ClientStatusDoesNotExistException(id));
		return ClientMapper.map(clientStatus);
	}
}
