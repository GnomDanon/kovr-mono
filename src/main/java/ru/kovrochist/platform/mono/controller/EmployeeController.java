package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.EmployeeApi;
import ru.kovrochist.platform.mono.dto.employee.CreateEmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.UpdateEmployeeDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

	private final EmployeeService employeeService;

	@Override
	public ResponseEntity<EmployeeDto> create(CreateEmployeeDto employee) throws DoesNotExistException {
		return ResponseEntity.ok(employeeService.create(employee));
	}

	@Override
	public ResponseEntity<EmployeeDto> update(UpdateEmployeeDto employee) throws DoesNotExistException {
		return ResponseEntity.ok(employeeService.update(employee));
	}

	@Override
	public ResponseEntity<EmployeeDto> get(UUID id) throws DoesNotExistException {
		return ResponseEntity.ok(employeeService.getById(id));
	}

	@Override
	public ResponseEntity<List<EmployeeDto>> getByRoleId(UUID id) {
		return ResponseEntity.ok(employeeService.getByRoleId(id));
	}

	@Override
	public ResponseEntity<List<EmployeeDto>> get() {
		return ResponseEntity.ok(employeeService.get());
	}
}
