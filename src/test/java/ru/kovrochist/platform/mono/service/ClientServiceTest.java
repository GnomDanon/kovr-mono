package ru.kovrochist.platform.mono.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.repository.ClientRepository;
import ru.kovrochist.platform.mono.type.Gender;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientServiceTest {

	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private ClientService clientService;

	private Clients testClient;
	private ClientDto testClientDto;
	private final Long testId = 1L;
	private final String testPhone = "+79998887766";
	private final String testCode = "123456";
	private final String testFirstName = "Иван";
	private final String testLastName = "Иванов";
	private final Date testBirthday = new Date();
	private final String testCity = "Москва";
	private final String testAddress = "ул. Пушкина, д.10";
	private final String testGenderLabel = "Мужской";

	@BeforeEach
	void setUp() {
		String testStatus = "ACTIVE";
		testClient = new Clients()
				.setId(testId)
				.setPhone(testPhone)
				.setFirstName(testFirstName)
				.setLastName(testLastName)
				.setBirthday(testBirthday)
				.setCity(testCity)
				.setAddress(testAddress)
				.setGender(Gender.MALE)
				.setStatus(testStatus)
				.setCode(testCode);

		testClientDto = new ClientDto()
				.setStatus(testStatus);

		testClientDto.setPhone(testPhone)
				.setFirstName(testFirstName)
				.setLastName(testLastName)
				.setBirthday(testBirthday)
				.setCity(testCity)
				.setAddress(testAddress)
				.setGender(testGenderLabel);
	}

	@Test
	@DisplayName("Получение клиентов")
	void getClients_ShouldReturnListOfClientDto() {
		List<Clients> clientsList = Collections.singletonList(testClient);
		when(clientRepository.findAll()).thenReturn(clientsList);

		List<ClientDto> result = clientService.getClients();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(testPhone, result.get(0).getPhone());
		verify(clientRepository).findAll();
	}

	@Test
	@DisplayName("Получение клиентов из пустой базы")
	void getClients_WhenNoClients_ShouldReturnEmptyList() {
		when(clientRepository.findAll()).thenReturn(Collections.emptyList());

		List<ClientDto> result = clientService.getClients();

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("Получение клиента по идентификатору")
	void getClient_WithExistingId_ShouldReturnClientDto() throws ClientDoesNotExistException {
		when(clientRepository.findById(testId)).thenReturn(Optional.of(testClient));

		ClientDto result = clientService.getClient(testId);

		assertNotNull(result);
		assertEquals(testPhone, result.getPhone());
		assertEquals(testFirstName, result.getFirstName());
		verify(clientRepository).findById(testId);
	}

	@Test
	@DisplayName("Получение клиента по несуществующему идентификатору")
	void getClient_WithNonExistingId_ShouldThrowException() {
		when(clientRepository.findById(testId)).thenReturn(Optional.empty());

		assertThrows(ClientDoesNotExistException.class, () -> clientService.getClient(testId));
	}

	@Test
	@DisplayName("Получение клиентов по поисковой строке")
	void getClientsWithSearch_ShouldReturnFilteredList() {
		List<Clients> clientsList = Collections.singletonList(testClient);
		String testSearch = "Иван";
		when(clientRepository.find("%" + testSearch + "%")).thenReturn(clientsList);

		List<ClientDto> result = clientService.getClients(testSearch);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(testLastName, result.get(0).getLastName());
		verify(clientRepository).find("%" + testSearch + "%");
	}

	@Test
	@DisplayName("Обновление клиента")
	void update_WithExistingId_ShouldReturnUpdatedClientDto() throws DoesNotExistException {
		when(clientRepository.findById(testId)).thenReturn(Optional.of(testClient));
		when(clientRepository.save(any(Clients.class))).thenReturn(testClient);

		ClientDto result = clientService.update(testId, testClientDto);

		assertNotNull(result);
		assertEquals(testPhone, result.getPhone());
		assertEquals(testGenderLabel, result.getGender());
		verify(clientRepository).save(any(Clients.class));
	}

	@Test
	@DisplayName("Обновление клиента по несуществующему идентификатору")
	void update_WithNonExistingId_ShouldThrowException() {
		when(clientRepository.findById(testId)).thenReturn(Optional.empty());

		assertThrows(ClientDoesNotExistException.class, () -> clientService.update(testId, testClientDto));
	}

	@Test
	@DisplayName("Получение клиента по номеру телефона")
	void getByPhone_WithExistingPhone_ShouldReturnClient() {
		when(clientRepository.findByPhone(testPhone)).thenReturn(Optional.of(testClient));

		Clients result = clientService.getByPhone(testPhone);

		assertNotNull(result);
		assertEquals(testPhone, result.getPhone());
	}

	@Test
	@DisplayName("Получение клиента по несуществующему номеру телефона")
	void getByPhone_WithNonExistingPhone_ShouldReturnNull() {
		when(clientRepository.findByPhone(testPhone)).thenReturn(Optional.empty());

		Clients result = clientService.getByPhone(testPhone);

		assertNull(result);
	}

	@Test
	@DisplayName("Регистрация клиента")
	void createWithPhoneAndCode_ShouldReturnNewClient() {
		when(clientRepository.save(any(Clients.class))).thenReturn(testClient);

		Clients result = clientService.create(testPhone, testCode);

		assertNotNull(result);
		assertEquals(testPhone, result.getPhone());
		assertEquals(testCode, result.getCode());
		verify(clientRepository).save(any(Clients.class));
	}

	@Test
	@DisplayName("Регистрация клиента с полной информацией о профиле")
	void createWithFullData_ShouldReturnNewClient() {
		when(clientRepository.save(any(Clients.class))).thenReturn(testClient);

		Clients result = clientService.create(testPhone, testFirstName, testLastName, testCity, testAddress);

		assertNotNull(result);
		assertEquals(testPhone, result.getPhone());
		assertEquals(testFirstName, result.getFirstName());
		verify(clientRepository).save(any(Clients.class));
	}

	@Test
	@DisplayName("Сохранение кода доступа")
	void setCode_ShouldUpdateClientCode() {
		clientService.setCode(testClient, testCode);

		assertEquals(testCode, testClient.getCode());
		verify(clientRepository).save(testClient);
	}

	@Test
	@DisplayName("Обновление информации о дне рождения")
	void update_WithNullBirthday_ShouldPreserveOriginalBirthday() throws DoesNotExistException {
		Date originalBirthday = new Date();
		Clients clientWithBirthday = new Clients()
				.setId(testId)
				.setBirthday(originalBirthday);

		when(clientRepository.findById(testId)).thenReturn(Optional.of(clientWithBirthday));
		when(clientRepository.save(any(Clients.class))).thenReturn(clientWithBirthday);

		ClientDto dtoWithNullBirthday = new ClientDto();
		dtoWithNullBirthday.setBirthday(null);

		ClientDto result = clientService.update(testId, dtoWithNullBirthday);

		assertNotNull(result);
		assertEquals(originalBirthday, clientWithBirthday.getBirthday());
	}
}
