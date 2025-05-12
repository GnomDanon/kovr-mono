package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

public class RoleDoesNotExistsException extends DoesNotExistException {

	public RoleDoesNotExistsException(String label) {
		super("Не существует роли" + label);
	}
}
