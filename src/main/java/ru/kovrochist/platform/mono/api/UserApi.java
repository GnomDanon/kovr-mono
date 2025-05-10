package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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

@RequestMapping("/user")
@Tag(name = "Пользователь")
public interface UserApi {

	@Operation(summary = "Получение профиля")
	@GetMapping("/me")
	ResponseEntity<UserDto> getProfile();

	@Operation(summary = "Обновление профиля")
	@PatchMapping("/profile")
	ResponseEntity<UserDto> updateProfile(@RequestBody ProfileFormData profile);

	@Operation(summary = "Получение аватара")
	@GetMapping("/avatar")
	ResponseEntity<String> getAvatar();

	@Operation(summary = "Загрузка аватара")
	@PostMapping("/avatar")
	ResponseEntity<String> uploadAvatar(@RequestParam("avatar") MultipartFile file);

	@Operation(summary = "Удаление аватара")
	@DeleteMapping("/avatar")
	ResponseEntity<String> deleteAvatar();
}
