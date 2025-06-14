package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.user.RoleWrapper;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;

import java.util.List;
import java.util.Map;

@RequestMapping("/staff")
@Tag(name = "Сотрудник")
public interface EmployeeApi {

	@Operation(summary = "Получение сотрудников")
	@GetMapping
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<EmployeeDto>> getEmployees();

	@Operation(summary = "Получение сотрудника")
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('OPERATOR', 'COURIER', 'MASTER', 'ADMIN')")
	ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) throws EmployeeDoesNotExistException;

	@Operation(summary = "Получение отфильтрованных сотрудников")
	@GetMapping("/filter")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<EmployeeDto>> fetchFilteredEmployees(@RequestParam Map<String, String> allParams);

	@Operation(summary = "Создание сотрудника")
	@PostMapping
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto user) throws DoesNotExistException, EmployeeAlreadyExistsException;

	@Operation(summary = "Обновление роли сотрудника")
	@PatchMapping("/{id}/role")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<EmployeeDto> updateEmployeeRole(@PathVariable Long id, @RequestBody RoleWrapper roleWrapper) throws DoesNotExistException;
}
