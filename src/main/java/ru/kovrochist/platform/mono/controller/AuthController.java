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
	public ResponseEntity<String> auth(String phone) {
		return ResponseEntity.ok(authService.auth(phone));
	}

	@Override
	public ResponseEntity<String> verify(String phone, String code) {
		return ResponseEntity.ok(authService.logout());
	}

	@Override
	public ResponseEntity<String> logout() {
		return ResponseEntity.ok(authService.logout());
	}
}
