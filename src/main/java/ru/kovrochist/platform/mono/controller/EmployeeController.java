package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.EmployeeApi;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.type.EmployeeRole;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {
	@Override
	public ResponseEntity<EmployeeDto> create(EmployeeDto employee) throws DoesNotExistException {
		return null;
	}

	@Override
	public ResponseEntity<EmployeeDto> update(EmployeeDto employee) throws DoesNotExistException {
		return null;
	}

	@Override
	public ResponseEntity<List<EmployeeDto>> get(String name, String phone, EmployeeRole role) {
		return null;
	}

	@Override
	public ResponseEntity<EmployeeDto> get(UUID id) throws DoesNotExistException {
		return null;
	}
}
