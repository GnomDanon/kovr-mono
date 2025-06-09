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
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.request.RequestDto;
import ru.kovrochist.platform.mono.exception.Advice;
import ru.kovrochist.platform.mono.service.RequestService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RequestControllerTest {

	@Mock
	private RequestService requestService;

	@InjectMocks
	private RequestController requestController;

	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(requestController).setControllerAdvice(new Advice()).build();
	}

	@Test
	@DisplayName("Создание заявки")
	void shouldCreateRequest() throws Exception {
		RequestDto request = new RequestDto();
		OrderDto response = new OrderDto(1L);
		when(requestService.create(request)).thenReturn(response);

		mockMvc.perform(post("/request")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(response)));
	}
}
