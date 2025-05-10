package ru.kovrochist.platform.mono.exception.employee;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

public class EmployeeDoesNotExistException extends DoesNotExistException {
	public EmployeeDoesNotExistException(Long id) {
		super(String.format("Сотрудник с идентификатором %d не найден", id));
	}
}
