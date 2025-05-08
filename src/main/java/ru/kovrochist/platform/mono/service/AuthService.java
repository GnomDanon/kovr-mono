package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.auth.JwtAuthenticationDto;
import ru.kovrochist.platform.mono.dto.auth.LoginDto;
import ru.kovrochist.platform.mono.dto.auth.RefreshTokenDto;
import ru.kovrochist.platform.mono.dto.auth.UserCredentialsDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.entity.User;
import ru.kovrochist.platform.mono.security.code.CodeGenerator;
import ru.kovrochist.platform.mono.security.jwt.JwtService;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;

	private final EmployeeService employeeService;
	private final ClientService clientService;
	private final CodeGenerator codeGenerator;

	public LoginDto login(LoginDto login) {
		String phone = login.getTempPhone();
		String code = passwordEncoder.encode(codeGenerator.generate());

		Employees employee = employeeService.getByPhone(phone);
		if (employee != null) {
			employeeService.setCode(employee, code);
			return login;
		}

		Clients client = clientService.getByPhone(phone);
		if (client != null) {
			clientService.setCode(client, code);
			return login;
		}

		clientService.create(phone, code);
		return login;
	}

	public JwtAuthenticationDto confirm(UserCredentialsDto credentials) throws AuthenticationException {
		String phone = credentials.getTempPhone();
		String code = credentials.getCode();

		User user = findUser(phone);

		if (!passwordEncoder.matches(code, user.getCode()))
			throw new AuthenticationException("Введен не верный код");

		return jwtService.generateAuthToken(phone);
	}

	public JwtAuthenticationDto refresh(RefreshTokenDto refresh) throws AuthenticationException {
		String refreshToken = refresh.getRefreshToken();
		if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
			User user = findUser(jwtService.getPhoneFromToken(refreshToken));
			return jwtService.refreshBaseToken(user.getPhone(), refreshToken);
		}
		throw new AuthenticationException("Невалидный токен");
	}

	private User findUser(String phone) throws AuthenticationException {
		User user = employeeService.getByPhone(phone);
		if (user == null)
			user = clientService.getByPhone(phone);
		if (user == null)
			throw new AuthenticationException("Не найден указанный номер телефона");
		return user;
	}
}
