package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.AuthApi;
import ru.kovrochist.platform.mono.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

	private final AuthService authService;

	@Override
	public ResponseEntity<String> signUp() {
		return ResponseEntity.ok(authService.signUp());
	}

	@Override
	public ResponseEntity<String> signIn() {
		return ResponseEntity.ok(authService.signIn());
	}

	@Override
	public ResponseEntity<String> logout() {
		return ResponseEntity.ok(authService.logout());
	}
}
