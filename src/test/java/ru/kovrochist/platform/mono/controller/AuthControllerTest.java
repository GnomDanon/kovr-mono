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
import ru.kovrochist.platform.mono.dto.auth.ConfirmResponse;
import ru.kovrochist.platform.mono.dto.auth.JwtAuthenticationDto;
import ru.kovrochist.platform.mono.dto.auth.LoginDto;
import ru.kovrochist.platform.mono.dto.auth.RefreshTokenDto;
import ru.kovrochist.platform.mono.dto.auth.UserCredentialsDto;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.exception.Advice;
import ru.kovrochist.platform.mono.service.AuthService;
import ru.kovrochist.platform.mono.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

	@Mock
	private AuthService authService;

	@Mock
	private UserService userService;

	@InjectMocks
	private AuthController authController;

	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(authController).setControllerAdvice(new Advice()).build();
	}

	@Test
	void shouldLogin() throws Exception {
		LoginDto request = new LoginDto();
		request.setTempPhone("79000000000");

		LoginDto response = new LoginDto();
		response.setTempPhone("79000000000");

		when(authService.login(request)).thenReturn(response);

		mockMvc.perform(post("/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(response)));
	}

	@Test
	@DisplayName("Подтверждение кода")
	void shouldConfirmCode() throws Exception {
		UserCredentialsDto credentials = new UserCredentialsDto();
		credentials.setTempPhone("79000000000");
		credentials.setCode("1234");

		JwtAuthenticationDto tokens = new JwtAuthenticationDto();
		tokens.setAccessToken("access");
		tokens.setRefreshToken("refresh");

		UserDto user = new UserDto();
		user.setId(1L);
		user.setFirstName("Иван");

		ConfirmResponse response = new ConfirmResponse().setTokens(tokens).setUser(user);

		when(authService.confirm(credentials)).thenReturn(tokens);
		when(userService.findUser("79000000000")).thenReturn(user);

		mockMvc.perform(post("/auth/confirm")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(credentials)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(response)));
	}

	@Test
	@DisplayName("Обновление токена")
	void shouldRefreshToken() throws Exception {
		RefreshTokenDto refresh = new RefreshTokenDto();
		refresh.setRefreshToken("old-token");

		JwtAuthenticationDto newTokens = new JwtAuthenticationDto();
		newTokens.setAccessToken("new-access");
		newTokens.setRefreshToken("new-refresh");

		when(authService.refresh(refresh)).thenReturn(newTokens);

		mockMvc.perform(post("/auth/refresh")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(refresh)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(newTokens)));
	}
}
