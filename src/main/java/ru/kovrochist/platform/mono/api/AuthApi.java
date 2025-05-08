package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovrochist.platform.mono.dto.auth.ConfirmResponse;
import ru.kovrochist.platform.mono.dto.auth.JwtAuthenticationDto;
import ru.kovrochist.platform.mono.dto.auth.LoginDto;
import ru.kovrochist.platform.mono.dto.auth.RefreshTokenDto;
import ru.kovrochist.platform.mono.dto.auth.UserCredentialsDto;

import javax.naming.AuthenticationException;

@RequestMapping(AuthApi.AUTH_PATH)
@Tag(name = "Авторизация")
public interface AuthApi {

	String AUTH_PATH = "/auth";
	String LOGIN_ENTRY_POINT = "/login";
	String CONFIRM_ENTRY_POINT = "/confirm";
	String REFRESH_ENTRY_POINT = "/refresh";

	@Operation(summary = "Авторизация")
	@PostMapping("/login")
	ResponseEntity<LoginDto> login(@RequestBody LoginDto login);

	@Operation(summary = "Проверка кода")
	@PostMapping("/confirm")
	ResponseEntity<ConfirmResponse> confirm(@RequestBody UserCredentialsDto credentials) throws AuthenticationException;

	@Operation(summary = "Обновление токена")
	@PostMapping("/refresh")
	ResponseEntity<JwtAuthenticationDto> refresh(@RequestBody RefreshTokenDto refresh) throws AuthenticationException;
}
