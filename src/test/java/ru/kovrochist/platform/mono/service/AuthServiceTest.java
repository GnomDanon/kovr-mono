package ru.kovrochist.platform.mono.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kovrochist.platform.mono.dto.auth.JwtAuthenticationDto;
import ru.kovrochist.platform.mono.dto.auth.LoginDto;
import ru.kovrochist.platform.mono.dto.auth.RefreshTokenDto;
import ru.kovrochist.platform.mono.dto.auth.UserCredentialsDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.security.code.CodeGenerator;
import ru.kovrochist.platform.mono.security.jwt.JwtService;

import javax.naming.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

	@Mock
	private JwtService jwtService;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private ClientService clientService;

	@Mock
	private CodeGenerator codeGenerator;

	@InjectMocks
	private AuthService authService;

	private final String testPhone = "+79999999999";
	private final String testCode = "7645";
	private final String testEncodedCode = "7645";
	private final String testToken = "test_token";
	private final String testRefreshToken = "test_refresh_token";

	private LoginDto loginDto;
	private UserCredentialsDto credentialsDto;
	private RefreshTokenDto refreshTokenDto;
	private Employees testEmployee;
	private Clients testClient;

	@BeforeEach
	void setUp() {
		loginDto = new LoginDto().setTempPhone(testPhone);
		credentialsDto = new UserCredentialsDto().setTempPhone(testPhone).setCode(testCode);
		refreshTokenDto = new RefreshTokenDto().setRefreshToken(testRefreshToken);
		testEmployee = new Employees().setPhone(testPhone).setCode(testEncodedCode);
		testClient = new Clients().setPhone(testPhone).setCode(testEncodedCode);
	}

	@Test
	@DisplayName("Авторизация сотрудника")
	void login_WhenEmployeeExists_ShouldSetCodeAndReturnLoginDto() {
		when(passwordEncoder.encode(testCode)).thenReturn(testEncodedCode);
		when(codeGenerator.generate()).thenReturn(testCode);
		when(employeeService.getByPhone(testPhone)).thenReturn(testEmployee);

		LoginDto result = authService.login(loginDto);

		assertEquals(loginDto, result);
		verify(employeeService).setCode(testEmployee, testEncodedCode);
		verify(clientService, never()).getByPhone(any());
		verify(clientService, never()).create(any(), any());
	}

	@Test
	@DisplayName("Авторизация клиента")
	void login_WhenClientExists_ShouldSetCodeAndReturnLoginDto() {
		when(employeeService.getByPhone(testPhone)).thenReturn(null);
		when(clientService.getByPhone(testPhone)).thenReturn(testClient);
		when(codeGenerator.generate()).thenReturn(testCode);
		when(passwordEncoder.encode(testCode)).thenReturn(testEncodedCode);

		LoginDto result = authService.login(loginDto);

		assertEquals(loginDto, result);
		verify(clientService).setCode(testClient, testEncodedCode);
		verify(employeeService, never()).setCode(any(), any());
		verify(clientService, never()).create(any(), any());
	}

	@Test
	@DisplayName("Регистрация клиента")
	void login_WhenUserNotExists_ShouldCreateClientAndReturnLoginDto() {
		when(employeeService.getByPhone(testPhone)).thenReturn(null);
		when(clientService.getByPhone(testPhone)).thenReturn(null);
		when(codeGenerator.generate()).thenReturn(testCode);
		when(passwordEncoder.encode(testCode)).thenReturn(testEncodedCode);

		LoginDto result = authService.login(loginDto);

		assertEquals(loginDto, result);
		verify(clientService).create(testPhone, testEncodedCode);
		verify(employeeService, never()).setCode(any(), any());
		verify(clientService, never()).setCode(any(), any());
	}

	@Test
	@DisplayName("Подтверждение кода сотрудника")
	void confirm_WithValidEmployeeCode_ShouldReturnAuthToken() throws AuthenticationException {
		when(employeeService.getByPhone(testPhone)).thenReturn(testEmployee);
		when(passwordEncoder.matches(testCode, testEncodedCode)).thenReturn(true);
		when(jwtService.generateAuthToken(testPhone)).thenReturn(new JwtAuthenticationDto().setAccessToken(testToken).setRefreshToken(testRefreshToken));

		JwtAuthenticationDto result = authService.confirm(credentialsDto);

		assertNotNull(result);
		assertEquals(testToken, result.getAccessToken());
		assertEquals(testRefreshToken, result.getRefreshToken());
	}

	@Test
	@DisplayName("Подтверждение кода клиента")
	void confirm_WithValidClientCode_ShouldReturnAuthToken() throws AuthenticationException {
		when(employeeService.getByPhone(testPhone)).thenReturn(null);
		when(clientService.getByPhone(testPhone)).thenReturn(testClient);
		when(passwordEncoder.matches(testCode, testEncodedCode)).thenReturn(true);
		when(jwtService.generateAuthToken(testPhone)).thenReturn(new JwtAuthenticationDto().setAccessToken(testToken).setRefreshToken(testRefreshToken));

		JwtAuthenticationDto result = authService.confirm(credentialsDto);

		assertNotNull(result);
		assertEquals(testToken, result.getAccessToken());
		assertEquals(testRefreshToken, result.getRefreshToken());
	}

	@Test
	@DisplayName("Выброс ошибки при передаче невалидного кода")
	void confirm_WithInvalidCode_ShouldThrowAuthenticationException() {
		when(employeeService.getByPhone(testPhone)).thenReturn(testEmployee);
		when(passwordEncoder.matches(testCode, testEncodedCode)).thenReturn(false);

		assertThrows(AuthenticationException.class, () -> authService.confirm(credentialsDto));
	}

	@Test
	@DisplayName("Выброс ошибки при отсутствии пользователя")
	void confirm_WhenUserNotFound_ShouldThrowAuthenticationException() {
		when(employeeService.getByPhone(testPhone)).thenReturn(null);
		when(clientService.getByPhone(testPhone)).thenReturn(null);

		assertThrows(AuthenticationException.class, () -> authService.confirm(credentialsDto));
	}

	@Test
	@DisplayName("Обновление токена")
	void refresh_WithValidRefreshToken_ShouldReturnNewAuthToken() throws AuthenticationException {
		when(jwtService.validateJwtToken(testRefreshToken)).thenReturn(true);
		when(jwtService.getPhoneFromToken(testRefreshToken)).thenReturn(testPhone);
		when(employeeService.getByPhone(testPhone)).thenReturn(testEmployee);
		when(jwtService.refreshBaseToken(testPhone, testRefreshToken))
				.thenReturn(new JwtAuthenticationDto().setAccessToken(testToken).setRefreshToken("new_refreshToken"));

		JwtAuthenticationDto result = authService.refresh(refreshTokenDto);

		assertNotNull(result);
		assertEquals(testToken, result.getAccessToken());
	}

	@Test
	@DisplayName("Выброс ошибки при невалидном токене")
	void refresh_WithInvalidRefreshToken_ShouldThrowAuthenticationException() {
		when(jwtService.validateJwtToken(testRefreshToken)).thenReturn(false);

		assertThrows(AuthenticationException.class, () -> authService.refresh(refreshTokenDto));
	}

	@Test
	@DisplayName("Выброс ошибки при отсутствии пользователя")
	void refresh_WhenUserNotFound_ShouldThrowAuthenticationException() {
		when(jwtService.validateJwtToken(testRefreshToken)).thenReturn(true);
		when(jwtService.getPhoneFromToken(testRefreshToken)).thenReturn(testPhone);
		when(employeeService.getByPhone(testPhone)).thenReturn(null);
		when(clientService.getByPhone(testPhone)).thenReturn(null);

		assertThrows(AuthenticationException.class, () -> authService.refresh(refreshTokenDto));
	}
}
