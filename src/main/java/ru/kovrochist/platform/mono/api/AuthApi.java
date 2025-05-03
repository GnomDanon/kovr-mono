package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovrochist.platform.mono.dto.auth.AuthDto;
import ru.kovrochist.platform.mono.dto.auth.VerifyDto;
import ru.kovrochist.platform.mono.dto.user.UserDto;

@RequestMapping("/auth")
@Tag(name = "Авторизация")
public interface AuthApi {

	@Operation(summary = "Авторизация")
	@PostMapping("/login")
	ResponseEntity<AuthDto> auth(@RequestBody AuthDto authDto);

	@Operation(summary = "Проверка кода")
	@PostMapping("/confirm")
	ResponseEntity<UserDto> verify(@RequestBody VerifyDto verifyDto);

	@Operation(summary = "Выход")
	@PostMapping("/logout")
	ResponseEntity<String> logout();
}
