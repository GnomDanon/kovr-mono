package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.EmployeeApi;
import ru.kovrochist.platform.mono.dto.user.UserDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

	@Override
	public ResponseEntity<List<UserDto>> getEmployees() {
		return null;
	}

	@Override
	public ResponseEntity<UserDto> createEmployee(UserDto user) {
		return null;
	}

	@Override
	public ResponseEntity<String> deactivateEmployee(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<UserDto> updateEmployeeRole() {
		return null;
	}
}
