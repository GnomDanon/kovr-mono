package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.EmployeeApi;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.user.RoleWrapper;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.filter.EmployeeFilter;
import ru.kovrochist.platform.mono.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

	private final EmployeeService employeeService;

	@Override
	public ResponseEntity<List<EmployeeDto>> getEmployees() {
		return ResponseEntity.ok(employeeService.getEmployees());
	}

	@Override
	public ResponseEntity<EmployeeDto> getEmployeeById(Long id) throws EmployeeDoesNotExistException {
		return ResponseEntity.ok(employeeService.getEmployee(id));
	}

	@Override
	public ResponseEntity<List<EmployeeDto>> fetchFilteredEmployees(Map<String, String> allParams) {
		return ResponseEntity.ok(employeeService.getEmployees(new EmployeeFilter(allParams)));
	}

	@Override
	public ResponseEntity<EmployeeDto> createEmployee(EmployeeDto user) throws DoesNotExistException, EmployeeAlreadyExistsException {
		return ResponseEntity.ok(employeeService.createEmployee(user));
	}

	@Override
	public ResponseEntity<String> deactivateEmployee(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<EmployeeDto> updateEmployeeRole(Long id, RoleWrapper role) throws DoesNotExistException {
		return ResponseEntity.ok(employeeService.updateEmployeeRole(id, role));
	}
}
