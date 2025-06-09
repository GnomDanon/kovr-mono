package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.kovrochist.platform.mono.api.UserApi;
import ru.kovrochist.platform.mono.dto.user.ProfileFormData;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

	private final UserService userService;

	@Override
	public ResponseEntity<UserDto> getProfile() throws EmployeeDoesNotExistException, ClientDoesNotExistException {
		return ResponseEntity.ok(userService.getProfile());
	}

	@Override
	public ResponseEntity<UserDto> updateProfile(ProfileFormData profile) throws DoesNotExistException {
		return ResponseEntity.ok(userService.updateProfile(profile));
	}

	@Override
	public ResponseEntity<String> getAvatar() {
		return null;
	}

	@Override
	public ResponseEntity<String> uploadAvatar(MultipartFile file)  {
		return null;
	}

	@Override
	public ResponseEntity<String> deleteAvatar() {
		return null;
	}
}
