package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.EmployeeApi;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.user.RoleWrapper;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.ResourceAccessException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.filter.EmployeeFilter;
import ru.kovrochist.platform.mono.security.access.AccessFilter;
import ru.kovrochist.platform.mono.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

	private final AccessFilter accessFilter;

	private final EmployeeService employeeService;

	@Override
	public ResponseEntity<List<EmployeeDto>> getEmployees() throws ResourceAccessException {
//		accessFilter.operatorOrAdmin();
		return ResponseEntity.ok(employeeService.getEmployees());
	}

	@Override
	public ResponseEntity<EmployeeDto> getEmployeeById(Long id) throws EmployeeDoesNotExistException, ResourceAccessException {
//		accessFilter.employee();
		return ResponseEntity.ok(employeeService.getEmployee(id));
	}

	@Override
	public ResponseEntity<List<EmployeeDto>> fetchFilteredEmployees(Map<String, String> allParams) throws ResourceAccessException {
//		accessFilter.operatorOrAdmin();
		return ResponseEntity.ok(employeeService.getEmployees(new EmployeeFilter(allParams)));
	}

	@Override
	public ResponseEntity<EmployeeDto> createEmployee(EmployeeDto user) throws DoesNotExistException, EmployeeAlreadyExistsException, ResourceAccessException {
//		accessFilter.operatorOrAdmin();
		return ResponseEntity.ok(employeeService.createEmployee(user));
	}

	@Override
	public ResponseEntity<String> deactivateEmployee(Long id) throws ResourceAccessException {
//		accessFilter.operatorOrAdmin();
		return null;
	}

	@Override
	public ResponseEntity<EmployeeDto> updateEmployeeRole(Long id, RoleWrapper role) throws DoesNotExistException, ResourceAccessException {
//		accessFilter.operatorOrAdmin();
		return ResponseEntity.ok(employeeService.updateEmployeeRole(id, role));
	}
}
