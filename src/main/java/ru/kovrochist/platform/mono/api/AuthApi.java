package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Tag(name = "Авторизация")
public interface AuthApi {

	@Operation(summary = "Авторизация")
	@PostMapping
	ResponseEntity<String> auth(String phone);

	@Operation(summary = "Проверка кода")
	@PostMapping("/verify")
	ResponseEntity<String> verify(String phone, String code);

	@Operation(summary = "Выход")
	@PostMapping("/logout")
	ResponseEntity<String> logout();
}
