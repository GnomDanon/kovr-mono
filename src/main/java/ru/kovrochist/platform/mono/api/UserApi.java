package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	@PreAuthorize("hasAnyRole('client', 'operator', 'courier', 'master', 'admin')")
	ResponseEntity<UserDto> getProfile() throws EmployeeDoesNotExistException, ClientDoesNotExistException;

	@Operation(summary = "Обновление профиля")
	@PatchMapping("/profile")
	@PreAuthorize("hasAnyRole('client', 'operator', 'courier', 'master', 'admin')")
	ResponseEntity<UserDto> updateProfile(@RequestBody ProfileFormData profile) throws DoesNotExistException;

	@Operation(summary = "Получение аватара")
	@GetMapping("/avatar")
	@PreAuthorize("hasAnyRole('client', 'operator', 'courier', 'master', 'admin')")
	ResponseEntity<String> getAvatar();

	@Operation(summary = "Загрузка аватара")
	@PostMapping("/avatar")
	@PreAuthorize("hasAnyRole('client', 'operator', 'courier', 'master', 'admin')")
	ResponseEntity<String> uploadAvatar(@RequestParam("avatar") MultipartFile file);

	@Operation(summary = "Удаление аватара")
	@DeleteMapping("/avatar")
	@PreAuthorize("hasAnyRole('client', 'operator', 'courier', 'master', 'admin')")
	ResponseEntity<String> deleteAvatar();
}
