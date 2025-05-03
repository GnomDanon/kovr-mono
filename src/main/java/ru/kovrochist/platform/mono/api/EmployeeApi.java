package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.type.EmployeeRole;

import java.util.List;
import java.util.UUID;

@RequestMapping("/employee")
@Tag(name = "Сотрудник")
public interface EmployeeApi {

	@Operation(summary = "Регистрация нового сотрудника в системе")
	@PostMapping
	ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employee) throws DoesNotExistException;

	@Operation(summary = "Обновление информации о сотруднике")
	@PutMapping
	ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employee) throws DoesNotExistException;

	@Operation(summary = "Получение информации о сотрудниках")
	@GetMapping
	ResponseEntity<List<EmployeeDto>> get(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "phone", required = false) String phone,
			@RequestParam(name = "role", required = false) EmployeeRole role
	);

	@Operation(summary = "Получение информации о сотруднике")
	@GetMapping("/get/{id}")
	ResponseEntity<EmployeeDto> get(@PathVariable UUID id) throws DoesNotExistException;
}
