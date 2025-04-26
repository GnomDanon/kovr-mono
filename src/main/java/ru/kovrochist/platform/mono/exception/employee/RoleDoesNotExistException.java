package ru.kovrochist.platform.mono.exception.employee;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.UUID;

public class RoleDoesNotExistException extends DoesNotExistException {

	public RoleDoesNotExistException(UUID id) {
		super(String.format("Роль с идентификатором %s не найдена", id.toString()));
	}
}
