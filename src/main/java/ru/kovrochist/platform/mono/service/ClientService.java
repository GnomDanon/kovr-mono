package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	public List<Clients> get() {
		List<Clients> result = new ArrayList<>();
		Iterable<Clients> clients = clientRepository.findAll();

		for (Clients client : clients) {
			result.add(client);
		}

		return result;
	}

	public List<Clients> get(String filter) {
		List<Clients> result = new ArrayList<>();
		Iterable<Clients> clients = clientRepository.find(filter);

		for (Clients client : clients) {
			result.add(client);
		}

		return result;
	}

	public Clients getById(UUID id) throws ClientDoesNotExistException {
		return clientRepository.findById(id).orElseThrow(() -> new ClientDoesNotExistException(id));
	}

	public Clients getByPhone(String phone) {
		return clientRepository.findByPhone(phone).orElse(null);
	}

	public Clients create(String phone, String firstName, String lastName, String city, String address) {
		Clients client = new Clients()
				.setPhone(phone)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setCity(city)
				.setAddress(address);

		return clientRepository.save(client);
	}
}
