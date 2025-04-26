package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Tag(name = "Авторизация")
public interface AuthApi {

	@Operation(summary = "Регистрация")
	@PostMapping("/sign-up")
	ResponseEntity<String> signUp();

	@Operation(summary = "Авторизация")
	@PutMapping("/sign-in")
	ResponseEntity<String> signIn();

	@Operation(summary = "Выход")
	@PostMapping("/logout")
	ResponseEntity<String> logout();
}
