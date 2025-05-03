package ru.kovrochist.platform.mono.exception.employee;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.UUID;

public class EmployeeDoesNotExistException extends DoesNotExistException {
	public EmployeeDoesNotExistException(Long id) {
		super(String.format("Сотрудник с идентификатором %d не найден", id));
	}
}
