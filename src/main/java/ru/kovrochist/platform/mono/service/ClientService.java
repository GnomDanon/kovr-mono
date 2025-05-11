package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.client.ClientMapper;
import ru.kovrochist.platform.mono.repository.ClientRepository;
import ru.kovrochist.platform.mono.type.Gender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	public List<ClientDto> getClients() {
		List<Clients> clients = get();
		return clients.stream().map(ClientMapper::map).collect(Collectors.toList());
	}

	public List<ClientDto> getClients(String search) {
		List<Clients> clients = get(search);
		return clients.stream().map(ClientMapper::map).collect(Collectors.toList());
	}

	public ClientDto update(Long id, ClientDto profile) throws DoesNotExistException {
		Clients client = update(id, profile.getPhone(), profile.getFirstName(), profile.getLastName(), profile.getBirthday(), profile.getCity(), profile.getAddress(), profile.getGender(), profile.getStatus());
		return ClientMapper.map(client);
	}

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

	public Clients getById(Long id) throws ClientDoesNotExistException {
		return clientRepository.findById(id).orElseThrow(() -> new ClientDoesNotExistException(id));
	}

	public Clients getByPhone(String phone) {
		return clientRepository.findByPhone(phone).orElse(null);
	}

	public Clients create(String phone, String code) {
		Clients client = new Clients().setPhone(phone).setCode(code);
		return clientRepository.save(client);
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

	public Clients update(Long id, String phone, String firstName, String lastName, Date birthday, String city, String address, String gender) throws DoesNotExistException {
		Clients client = getById(id);
		return update(client, phone, firstName, lastName, birthday, city, address, gender, client.getStatus());
	}

	public Clients update(Long id, String phone, String firstName, String lastName, Date birthday, String city, String address, String genderLabel, String status) throws DoesNotExistException {
		Clients client = getById(id);
		return update(client, phone, firstName, lastName, birthday, city, address, genderLabel, status);
	}

	public Clients update(Clients client, String phone, String firstName, String lastName, Date birthday, String city, String address, String genderLabel, String status) throws DoesNotExistException {
		Gender gender = genderLabel == null ? null : Gender.byLabel(genderLabel);
		Date clientBirthday = client.getBirthday();
		if (clientBirthday != null)
			birthday = clientBirthday;
		return update(client, phone, firstName, lastName, birthday, city, address, gender, status);
	}

	public Clients update(Clients client, String phone, String firstName, String lastName, Date birthday, String city, String address, Gender gender, String status) {
		return clientRepository.save(client.setPhone(phone).setFirstName(firstName).setLastName(lastName).setBirthday(birthday).setCity(city).setAddress(address).setGender(gender).setStatus(status));
	}

	public Clients setCode(Clients client, String code) {
		return clientRepository.save(client.setCode(code));
	}
}
