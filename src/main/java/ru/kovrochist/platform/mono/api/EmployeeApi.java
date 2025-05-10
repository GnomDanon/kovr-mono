package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovrochist.platform.mono.dto.user.UserDto;

import java.util.List;

@RequestMapping("/staff")
@Tag(name = "Сотрудник")
public interface EmployeeApi {

	@Operation(summary = "Получение сотрудников")
	@GetMapping
	ResponseEntity<List<UserDto>> getEmployees();

	@Operation(summary = "Создание сотрудника")
	@PostMapping
	ResponseEntity<UserDto> createEmployee(UserDto user);

	@Operation(summary = "") //TODO: ???
	@DeleteMapping("/{id}")
	ResponseEntity<String> deactivateEmployee(@PathVariable Long id);

	ResponseEntity<UserDto> updateEmployeeRole(); //TODO: РОЛЬ
}
