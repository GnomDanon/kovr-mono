package ru.kovrochist.platform.mono.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.kovrochist.platform.mono.dto.metadata.TypeWrapper;
import ru.kovrochist.platform.mono.exception.Advice;
import ru.kovrochist.platform.mono.service.MetadataService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MetadataControllerTest {

	@Mock
	private MetadataService metadataService;

	@InjectMocks
	private MetadataController metadataController;

	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(metadataController).setControllerAdvice(new Advice()).build();
	}

	@Test
	@DisplayName("Получение услуг")
	void shouldReturnServices() throws Exception {
		List<String> services = List.of("Химчистка", "Вывоз");
		when(metadataService.getServices()).thenReturn(services);

		mockMvc.perform(get("/metadata/services"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(services)));
	}

	@Test
	@DisplayName("Создание услуги")
	void shouldAddService() throws Exception {
		TypeWrapper type = new TypeWrapper("Химчистка");
		List<String> updated = List.of("Химчистка");
		when(metadataService.createService("Химчистка")).thenReturn(updated);

		mockMvc.perform(post("/metadata/services")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Удаление услуги")
	void shouldDeleteService() throws Exception {
		List<String> remaining = List.of("Химчистка");
		when(metadataService.removeService("Вывоз")).thenReturn(remaining);

		mockMvc.perform(delete("/metadata/services")
						.param("service", "Вывоз"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(remaining)));
	}

	@Test
	@DisplayName("Получение городов")
	void shouldReturnCities() throws Exception {
		List<String> cities = List.of("Киров", "Москва");
		when(metadataService.getCities()).thenReturn(cities);

		mockMvc.perform(get("/metadata/cities"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(cities)));
	}

	@Test
	@DisplayName("Создание города")
	void shouldAddCity() throws Exception {
		TypeWrapper type = new TypeWrapper("Киров");
		List<String> updated = List.of("Киров", "Москва");
		when(metadataService.createCity("Киров")).thenReturn(updated);

		mockMvc.perform(post("/metadata/cities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Удаление города")
	void shouldDeleteCity() throws Exception {
		List<String> remaining = List.of("Москва");
		when(metadataService.removeCity("Киров")).thenReturn(remaining);

		mockMvc.perform(delete("/metadata/cities")
						.param("city", "Киров"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(remaining)));
	}

	@Test
	@DisplayName("Получение типов изделий")
	void shouldReturnProductTypes() throws Exception {
		List<String> types = List.of("Ковёр", "Дорожка");
		when(metadataService.getProducts()).thenReturn(types);

		mockMvc.perform(get("/metadata/product-types"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(types)));
	}

	@Test
	@DisplayName("Создание типа изделия")
	void shouldAddProductType() throws Exception {
		TypeWrapper type = new TypeWrapper("Ковёр");
		List<String> updated = List.of("Ковёр");
		when(metadataService.createProduct("Ковёр")).thenReturn(updated);

		mockMvc.perform(post("/metadata/product-types")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Удаление типа изделия")
	void shouldDeleteProductType() throws Exception {
		List<String> remaining = List.of("Ковёр");
		when(metadataService.removeProduct("Дорожка")).thenReturn(remaining);

		mockMvc.perform(delete("/metadata/product-types")
						.param("type", "Дорожка"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(remaining)));
	}

	@Test
	@DisplayName("Получение типов загрязнений")
	void shouldReturnContaminationTypes() throws Exception {
		List<String> types = List.of("Пятна", "Грязь");
		when(metadataService.getContaminations()).thenReturn(types);

		mockMvc.perform(get("/metadata/contamination-types"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(types)));
	}

	@Test
	@DisplayName("Создание типа загрязнения")
	void shouldAddContaminationType() throws Exception {
		TypeWrapper type = new TypeWrapper("Грязь");
		List<String> updated = List.of("Грязь", "Пыль");
		when(metadataService.createContamination("Грязь")).thenReturn(updated);

		mockMvc.perform(post("/metadata/contamination-types")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Ужаление типа загрязнения")
	void shouldDeleteContaminationType() throws Exception {
		List<String> remaining = List.of("Пыль");
		when(metadataService.removeContamination("Грязь")).thenReturn(remaining);

		mockMvc.perform(delete("/metadata/contamination-types")
						.param("type", "Грязь"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(remaining)));
	}

	@Test
	@DisplayName("Получение видов доставки")
	void shouldReturnDeliveryOptions() throws Exception {
		List<String> options = List.of("Самовывоз", "Курьер");
		when(metadataService.getDeliveryTypes()).thenReturn(options);

		mockMvc.perform(get("/metadata/delivery-options"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(options)));
	}

	@Test
	@DisplayName("Создание источника")
	void shouldAddRequestSource() throws Exception {
		TypeWrapper type = new TypeWrapper("Инстаграм");
		List<String> updated = List.of("Инстаграм", "Сайт");
		when(metadataService.createSource("Инстаграм")).thenReturn(updated);

		mockMvc.perform(post("/metadata/request-sources")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Получение источников")
	void shouldReturnRequestSources() throws Exception {
		List<String> sources = List.of("Сайт", "Инстаграм");
		when(metadataService.getSources()).thenReturn(sources);

		mockMvc.perform(get("/metadata/request-sources"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(sources)));
	}

	@Test
	@DisplayName("Удаление источника")
	void shouldDeleteRequestSource() throws Exception {
		List<String> remaining = List.of("Сайт");
		when(metadataService.removeSource("Инстаграм")).thenReturn(remaining);

		mockMvc.perform(delete("/metadata/request-sources")
						.param("source", "Инстаграм"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(remaining)));
	}

	@Test
	@DisplayName("Создание статуса клиента")
	void shouldAddClientStatus() throws Exception {
		TypeWrapper type = new TypeWrapper("Постоянный");
		List<String> updated = List.of("Постоянный", "Новый");
		when(metadataService.createClientStatus("Постоянный")).thenReturn(updated);

		mockMvc.perform(post("/metadata/client-statuses")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Получение статусов клиентов")
	void shouldReturnClientStatuses() throws Exception {
		List<String> statuses = List.of("Новый", "Постоянный");
		when(metadataService.getClientStatuses()).thenReturn(statuses);

		mockMvc.perform(get("/metadata/client-statuses"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(statuses)));
	}

	@Test
	@DisplayName("Удаление статуса клиента")
	void shouldDeleteClientStatus() throws Exception {
		List<String> remaining = List.of("Новый");
		when(metadataService.removeClientStatus("Постоянный")).thenReturn(remaining);

		mockMvc.perform(delete("/metadata/client-statuses")
						.param("status", "Постоянный"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(remaining)));
	}

	@Test
	@DisplayName("Создание района")
	void shouldAddDistrict() throws Exception {
		TypeWrapper type = new TypeWrapper("Октябрьский");
		List<String> updated = List.of("Октябрьский", "Ленинский");
		when(metadataService.createDistrict("Октябрьский")).thenReturn(updated);

		mockMvc.perform(post("/metadata/districts")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(type)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Получение районов")
	void shouldReturnDistricts() throws Exception {
		List<String> districts = List.of("Ленинский", "Октябрьский");
		when(metadataService.getDistricts()).thenReturn(districts);

		mockMvc.perform(get("/metadata/districts"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(districts)));
	}

	@Test
	@DisplayName("Удаление района")
	void shouldDeleteDistrict() throws Exception {
		List<String> remaining = List.of("Ленинский");
		when(metadataService.removeDistrict("Октябрьский")).thenReturn(remaining);

		mockMvc.perform(delete("/metadata/districts")
						.param("district", "Октябрьский"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(remaining)));
	}
}
