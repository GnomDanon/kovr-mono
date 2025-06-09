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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kovrochist.platform.mono.dto.user.ProfileFormData;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.security.user.CommonUserDetails;
import ru.kovrochist.platform.mono.security.user.User;
import ru.kovrochist.platform.mono.type.Role;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

	@Mock
	private ClientService clientService;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private User user;

	@InjectMocks
	private UserService userService;

	private final Long testId = 1L;
	private final String testPhone = "79998887766";

	private Clients testClient;
	private Employees testEmployee;
	private ProfileFormData testProfileForm;

	@BeforeEach
	void setUp() {
		String testFirstName = "Иван";
		String testLastName = "Иванов";
		String testCity = "Москва";
		testClient = new Clients()
				.setId(testId)
				.setPhone(testPhone)
				.setFirstName(testFirstName)
				.setLastName(testLastName)
				.setCity(testCity);

		testEmployee = new Employees()
				.setId(testId)
				.setPhone(testPhone)
				.setFirstName(testFirstName)
				.setLastName(testLastName)
				.setRole(Role.OPERATOR);

		testProfileForm = new ProfileFormData()
				.setPhone(testPhone)
				.setFirstName(testFirstName)
				.setLastName(testLastName)
				.setCity(testCity);

		when(user.getId()).thenReturn(testId);
	}

	@Test
	@DisplayName("Получение пользователя-сотрудника по номеру телефона")
	void loadUserByUsername_WhenEmployeeExists_ShouldReturnUserDetails() {
		when(employeeService.getByPhone(testPhone)).thenReturn(testEmployee);

		CommonUserDetails result = userService.loadUserByUsername(testPhone);

		assertNotNull(result);
		assertEquals(testPhone, result.getUsername());
		verify(employeeService).getByPhone(testPhone);
		verify(clientService, never()).getByPhone(any());
	}

	@Test
	@DisplayName("Получение пользователя-клиента по ")
	void loadUserByUsername_WhenClientExists_ShouldReturnUserDetails() {
		when(employeeService.getByPhone(testPhone)).thenReturn(null);
		when(clientService.getByPhone(testPhone)).thenReturn(testClient);

		CommonUserDetails result = userService.loadUserByUsername(testPhone);

		assertNotNull(result);
		assertEquals(testPhone, result.getUsername());
		verify(employeeService).getByPhone(testPhone);
		verify(clientService).getByPhone(testPhone);
	}

	@Test
	@DisplayName("Получение несуществующего пользователя")
	void loadUserByUsername_WhenUserNotFound_ShouldThrowException() {
		when(employeeService.getByPhone(testPhone)).thenReturn(null);
		when(clientService.getByPhone(testPhone)).thenReturn(null);

		assertThrows(UsernameNotFoundException.class, () ->
				userService.loadUserByUsername(testPhone));
	}

	@Test
	@DisplayName("Получение профиля клиента")
	void getProfile_WhenClient_ShouldReturnClientDto() throws Exception {
		when(user.getRole()).thenReturn(Role.CLIENT);
		when(clientService.getById(testId)).thenReturn(testClient);

		UserDto result = userService.getProfile();

		assertNotNull(result);
		assertEquals(testPhone, result.getPhone());
		verify(clientService).getById(testId);
		verify(employeeService, never()).getById(any());
	}

	@Test
	@DisplayName("Получение профиля сотрудника")
	void getProfile_WhenEmployee_ShouldReturnEmployeeDto() throws Exception {
		when(user.getRole()).thenReturn(Role.ADMIN);
		when(employeeService.getById(testId)).thenReturn(testEmployee);

		UserDto result = userService.getProfile();

		assertNotNull(result);
		assertEquals(testPhone, result.getPhone());
		verify(employeeService).getById(testId);
		verify(clientService, never()).getById(any());
	}

	@Test
	@DisplayName("Получение профиля несуществующего клиента")
	void getProfile_WhenClientNotFound_ShouldThrowException() throws Exception {
		when(user.getRole()).thenReturn(Role.CLIENT);
		when(clientService.getById(testId)).thenThrow(ClientDoesNotExistException.class);

		assertThrows(ClientDoesNotExistException.class, () ->
				userService.getProfile());
	}

	@Test
	@DisplayName("Получение профиля несуществующего сотрудника")
	void getProfile_WhenEmployeeNotFound_ShouldThrowException() throws Exception {
		when(user.getRole()).thenReturn(Role.OPERATOR);
		when(employeeService.getById(testId)).thenThrow(EmployeeDoesNotExistException.class);

		assertThrows(EmployeeDoesNotExistException.class, () ->
				userService.getProfile());
	}

	@Test
	@DisplayName("Обновление профиля клиента")
	void updateProfile_WhenClient_ShouldUpdateClient() throws Exception {
		when(user.getRole()).thenReturn(Role.CLIENT);
		when(clientService.update(eq(testId), any(), any(), any(), any(), any(), any(), any()))
				.thenReturn(testClient);

		UserDto result = userService.updateProfile(testProfileForm);

		assertNotNull(result);
		assertEquals(testPhone, result.getPhone());
		verify(clientService).update(eq(testId), any(), any(), any(), any(), any(), any(), any());
		verify(employeeService, never()).update(any(), any(), any(), any(), any(), any());
	}

	@Test
	@DisplayName("Обновление профиля сотрудника")
	void updateProfile_WhenEmployee_ShouldUpdateEmployee() throws Exception {
		when(user.getRole()).thenReturn(Role.OPERATOR);
		when(employeeService.update(eq(testId), any(), any(), any(), any(), any()))
				.thenReturn(testEmployee);

		UserDto result = userService.updateProfile(testProfileForm);

		assertNotNull(result);
		assertEquals(testPhone, result.getPhone());
		verify(employeeService).update(eq(testId), any(), any(), any(), any(), any());
		verify(clientService, never()).update(any(), any(), any(), any(), any(), any(), any(), any());
	}

	@Test
	@DisplayName("Ошибка при обновлении клиента")
	void updateProfile_WhenClientUpdateFails_ShouldThrowException() throws Exception {
		when(user.getRole()).thenReturn(Role.CLIENT);
		when(clientService.update(eq(testId), any(), any(), any(), any(), any(), any(), any()))
				.thenThrow(DoesNotExistException.class);

		assertThrows(DoesNotExistException.class, () ->
				userService.updateProfile(testProfileForm));
	}
}
