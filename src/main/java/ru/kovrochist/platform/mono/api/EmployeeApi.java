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
import ru.kovrochist.platform.mono.dto.employee.CreateEmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.UpdateEmployeeDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.List;
import java.util.UUID;

@RequestMapping("/employee")
@Tag(name = "Сотрудник")
public interface EmployeeApi {

	@Operation(summary = "Регистрация нового сотрудника в системе")
	@PostMapping("/create")
	ResponseEntity<EmployeeDto> create(@RequestBody CreateEmployeeDto employee) throws DoesNotExistException;

	@Operation(summary = "Обновление информации о сотруднике")
	@PutMapping("/update")
	ResponseEntity<EmployeeDto> update(@RequestBody UpdateEmployeeDto employee) throws DoesNotExistException;

	@Operation(summary = "Получение информации о сотруднике")
	@GetMapping("/get/{id}")
	ResponseEntity<EmployeeDto> get(@PathVariable UUID id) throws DoesNotExistException;

	@Operation(summary = "Получение информации о сотрудниках по идентификатору роли")
	@GetMapping("/get/byRole")
	ResponseEntity<List<EmployeeDto>> getByRoleId(@RequestParam UUID id);

	@Operation(summary = "Получение информации о сотрудниках")
	@GetMapping("/get")
	ResponseEntity<List<EmployeeDto>> get();
}
