package ru.kovrochist.platform.mono.exception.employee;

import ru.kovrochist.platform.mono.exception.ResourceConflictException;

public class EmployeeAlreadyExistsException extends ResourceConflictException {

	public EmployeeAlreadyExistsException(String phone) {
		super(String.format("Сотрудник с номером телефона %s уже существует", phone));
	}
}
