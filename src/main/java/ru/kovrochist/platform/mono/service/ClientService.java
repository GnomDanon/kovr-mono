package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.address.AddressDto;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.dto.client.CreateClientDto;
import ru.kovrochist.platform.mono.dto.client.UpdateClientDto;
import ru.kovrochist.platform.mono.dto.client.status.ClientStatusDto;
import ru.kovrochist.platform.mono.entity.Client;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.address.AddressMapper;
import ru.kovrochist.platform.mono.mapper.client.ClientMapper;
import ru.kovrochist.platform.mono.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	private final ClientStatusService clientStatusService;
	private final AddressService addressService;

	public ClientDto create(CreateClientDto clientDto) throws DoesNotExistException {
		ClientStatusDto clientStatus = clientStatusService.getById(clientDto.getClientStatusId());
		AddressDto address = addressService.create(clientDto.getAddress());

		Client client = clientRepository.save(ClientMapper.map(clientDto).setClientStatus(ClientMapper.map(clientStatus)).setAddress(AddressMapper.map(address)));
		return ClientMapper.map(client);
	}

	public ClientDto update(UpdateClientDto clientDto) throws DoesNotExistException {
		ClientStatusDto clientStatus = clientStatusService.getById(clientDto.getClientStatusId());

		Client client = clientRepository.findById(clientDto.getId()).orElseThrow(() -> new ClientDoesNotExistException(clientDto.getId()));
		clientRepository.save(client.setName(clientDto.getName()).setComment(clientDto.getComment()).setClientStatus(ClientMapper.map(clientStatus)));

		return ClientMapper.map(client);
	}

	public ClientDto getById(UUID id) throws DoesNotExistException {
		Client client = clientRepository.findById(id).orElseThrow(() -> new ClientDoesNotExistException(id));
		return ClientMapper.map(client);
	}

	public List<ClientDto> getByClientStatusId(UUID clientStatusId) {
		Iterable<Client> clients = clientRepository.findAllByClientStatusId(clientStatusId);
		List<ClientDto> result = new ArrayList<>();

		for (Client client : clients) {
			result.add(ClientMapper.map(client));
		}

		return result;
	}

	public List<ClientDto> get() {
		Iterable<Client> clients = clientRepository.findAll();
		List<ClientDto> result = new ArrayList<>();

		for (Client client : clients) {
			result.add(ClientMapper.map(client));
		}

		return result;
	}
}
