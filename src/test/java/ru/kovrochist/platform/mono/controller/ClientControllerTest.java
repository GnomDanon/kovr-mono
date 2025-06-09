package ru.kovrochist.platform.mono.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.exception.Advice;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.filter.ClientFilter;
import ru.kovrochist.platform.mono.service.ClientService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

	@Mock
	private ClientService clientService;

	@InjectMocks
	private ClientController clientController;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(clientController).setControllerAdvice(new Advice()).build();
	}

	@Test
	@DisplayName("Получение клиентов")
	public void testFetchClients() throws Exception {
		ClientDto client1 = new ClientDto(1L, "70000000001");
		ClientDto client2 = new ClientDto(2L, "70000000002");
		when(clientService.getClients()).thenReturn(List.of(client1, client2));

		mockMvc.perform(get("/clients")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].phone").value("70000000001"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].phone").value("70000000002"));
	}

	@Test
	@DisplayName("Получение клиентов из пустой таблицы")
	void testGetClients_NoClients() throws Exception {
		when(clientService.getClients()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/clients"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty());
	}

	@Test
	@DisplayName("Получение клиента по идентификатору")
	void testGetClientById_Success() throws Exception {
		ClientDto client = new ClientDto(1L, "70000000001");
		when(clientService.getClient(1L)).thenReturn(client);

		mockMvc.perform(get("/clients/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.phone").value("70000000001"));
	}

	@Test
	@DisplayName("Получение клиента по несуществующему идентификатору")
	@SuppressWarnings("deprecation")
	void testGetClientById_ClientNotFound() throws Exception {
		when(clientService.getClient(1L)).thenThrow(new ClientDoesNotExistException(1L));

		mockMvc.perform(get("/clients/{id}", 1L).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound())
				.andExpect(content().string("Клиент с идентификатором 1 не найден"));
	}

	@Test
	@DisplayName("Получение отфильтрованных клиентов")
	void testFetchFilteredClients_Success() throws Exception {
		List<ClientDto> clients = List.of(new ClientDto(1L, "70000000001"));
		when(clientService.getClients(any(ClientFilter.class))).thenReturn(clients);

		mockMvc.perform(get("/clients/filter").param("search", "70000000001"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].phone").value("70000000001"));
	}

	@Test
	@DisplayName("Получение клиента по номеру")
	void testSearchClients_Success() throws Exception {
		String query = "70000000001";

		List<ClientDto> clients = List.of(new ClientDto(1L, "70000000001"));
		when(clientService.getClients(query)).thenReturn(clients);

		mockMvc.perform(get("/clients/search").param("query", query))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].phone").value("70000000001"));
	}

	@Test
	@DisplayName("Получение клиента с несуществующим номером")
	void testSearchClients_EmptyResult() throws Exception {
		String query = "NonExistentClient";

		when(clientService.getClients(query)).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/clients/search").param("query", query))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty());
	}

	@Test
	@DisplayName("Обновление информации о клиенте")
	void testUpdateClientInfo_Success() throws Exception {
		ClientDto clientDto = new ClientDto(1L, "70000000001");
		when(clientService.update(1L, clientDto)).thenReturn(clientDto);

		mockMvc.perform(patch("/clients/{clientId}", 1L)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\": 1,\"phone\": \"70000000001\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.phone").value("70000000001"));
	}

	@Test
	@DisplayName("Обновление информации о несуществующем клиенте")
	@SuppressWarnings("deprecation")
	void testUpdateClientInfo_ClientNotFound() throws Exception {
		ClientDto clientDto = new ClientDto(1L, "70000000001");
		when(clientService.update(1L, clientDto)).thenThrow(new ClientDoesNotExistException(1L));

		mockMvc.perform(patch("/clients/{clientId}", 1L)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\": 1,\"phone\": \"70000000001\"}")
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound())
				.andExpect(content().string("Клиент с идентификатором 1 не найден"));
	}

	@Test
	@DisplayName("Выброс ошибки")
	@SuppressWarnings("deprecation")
	void testClientServiceThrowsException() throws Exception {
		when(clientService.getClients()).thenThrow(new RuntimeException("Service failure"));

		mockMvc.perform(get("/clients")
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Service failure"));
	}
}
