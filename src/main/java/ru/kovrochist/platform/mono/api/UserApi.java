package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovrochist.platform.mono.dto.user.ProfileFormData;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;

@RequestMapping("/user")
@Tag(name = "Пользователь")
public interface UserApi {

	@Operation(summary = "Получение профиля")
	@GetMapping("/me")
	@PreAuthorize("hasAnyRole('CLIENT', 'OPERATOR', 'COURIER', 'MASTER', 'ADMIN')")
	ResponseEntity<UserDto> getProfile() throws EmployeeDoesNotExistException, ClientDoesNotExistException;

	@Operation(summary = "Обновление профиля")
	@PatchMapping("/profile")
	@PreAuthorize("hasAnyRole('CLIENT', 'OPERATOR', 'COURIER', 'MASTER', 'ADMIN')")
	ResponseEntity<UserDto> updateProfile(@RequestBody ProfileFormData profile) throws DoesNotExistException;
}
