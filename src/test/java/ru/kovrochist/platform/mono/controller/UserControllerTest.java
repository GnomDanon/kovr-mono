package ru.kovrochist.platform.mono.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.kovrochist.platform.mono.dto.user.ProfileFormData;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.exception.Advice;
import ru.kovrochist.platform.mono.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new Advice()).build();
	}

	@Test
	void shouldReturnUserProfile() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setId(1L);
		userDto.setFirstName("Иван");

		when(userService.getProfile()).thenReturn(userDto);

		mockMvc.perform(get("/user/me"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(userDto)));
	}

	@Test
	void shouldUpdateUserProfile() throws Exception {
		ProfileFormData profile = new ProfileFormData();
		profile.setFirstName("Петр");

		UserDto updatedUser = new UserDto();
		updatedUser.setId(1L);
		updatedUser.setFirstName("Петр");

		when(userService.updateProfile(profile)).thenReturn(updatedUser);

		mockMvc.perform(patch("/user/profile")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(profile)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updatedUser)));
	}
}
