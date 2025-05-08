package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.AuthApi;
import ru.kovrochist.platform.mono.dto.auth.ConfirmResponse;
import ru.kovrochist.platform.mono.dto.auth.JwtAuthenticationDto;
import ru.kovrochist.platform.mono.dto.auth.LoginDto;
import ru.kovrochist.platform.mono.dto.auth.RefreshTokenDto;
import ru.kovrochist.platform.mono.dto.auth.UserCredentialsDto;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.security.user.UserService;
import ru.kovrochist.platform.mono.service.AuthService;

import javax.naming.AuthenticationException;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

	private final AuthService authService;
	private final UserService userService;

	@Override
	public ResponseEntity<LoginDto> login(LoginDto login) {
		return ResponseEntity.ok(authService.login(login));
	}

	@Override
	public ResponseEntity<ConfirmResponse> confirm(UserCredentialsDto credentials) throws AuthenticationException {
		JwtAuthenticationDto jwt = authService.confirm(credentials);
		UserDto user = userService.findUser(credentials.getTempPhone());
		return ResponseEntity.ok(new ConfirmResponse().setTokens(jwt).setUser(user));
	}

	@Override
	public ResponseEntity<JwtAuthenticationDto> refresh(RefreshTokenDto refresh) throws AuthenticationException {
		return ResponseEntity.ok(authService.refresh(refresh));
	}
}
