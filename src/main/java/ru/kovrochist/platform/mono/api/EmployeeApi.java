package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovrochist.platform.mono.dto.user.RoleWrapper;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeAlreadyExistsException;

import java.util.List;

@RequestMapping("/staff")
@Tag(name = "Сотрудник")
public interface EmployeeApi {

	@Operation(summary = "Получение сотрудников")
	@GetMapping
	ResponseEntity<List<UserDto>> getEmployees();

	@Operation(summary = "Создание сотрудника")
	@PostMapping
	ResponseEntity<UserDto> createEmployee(@RequestBody UserDto user) throws DoesNotExistException, EmployeeAlreadyExistsException;

	@Operation(summary = "") //TODO: ???
	@DeleteMapping("/{id}")
	ResponseEntity<String> deactivateEmployee(@PathVariable Long id);

	@Operation(summary = "Обновление роли клиента")
	@PatchMapping("/{id}/role")
	ResponseEntity<UserDto> updateEmployeeRole(@PathVariable Long id, @RequestBody RoleWrapper roleWrapper) throws DoesNotExistException;
}
