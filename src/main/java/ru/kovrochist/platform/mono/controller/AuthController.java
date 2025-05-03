package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.AuthApi;
import ru.kovrochist.platform.mono.dto.auth.AuthDto;
import ru.kovrochist.platform.mono.dto.auth.VerifyDto;
import ru.kovrochist.platform.mono.dto.user.RoleDto;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.service.AuthService;
import ru.kovrochist.platform.mono.type.Gender;
import ru.kovrochist.platform.mono.type.Role;
import ru.kovrochist.platform.mono.type.mutable.Cities;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

	private final AuthService authService;

	private final String DEFAULT_AVATAR = "https://camo.githubusercontent.com/ca27c35c7402af6e6215e257b5f97b934aeb7b47a3aa5622b28ec88989037220/68747470733a2f2f63646e2e6a7364656c6976722e6e65742f67682f616c6f68652f617661746172732f706e672f76696272656e745f372e706e67";

	@Override
	public ResponseEntity<AuthDto> auth(AuthDto authDto) {
		return ResponseEntity.ok(authDto);
	}

	@Override
	public ResponseEntity<UserDto> verify(VerifyDto verifyDto) {
		return ResponseEntity.ok(new UserDto()
				.setId(1L)
				.setPhone("79999999999")
				.setFirstName("Иван")
				.setLastName("Иванов")
				.setRole(new RoleDto(Role.OPERATOR.getLabel()))
				.setAvatar(DEFAULT_AVATAR)
				.setBirthday(new Date())
				.setCity(Cities.KIROV.getLabel())
				.setAddress("ул. Пушкина, д. 25")
				.setGender(Gender.MALE.getLabel()));
	}

	@Override
	public ResponseEntity<String> logout() {
		return ResponseEntity.ok(authService.logout());
	}
}
