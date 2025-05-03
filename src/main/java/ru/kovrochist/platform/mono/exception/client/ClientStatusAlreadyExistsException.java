package ru.kovrochist.platform.mono.exception.client;

import ru.kovrochist.platform.mono.exception.ResourceConflictException;

public class ClientStatusAlreadyExistsException extends ResourceConflictException {

	public ClientStatusAlreadyExistsException(String status) {
		super(String.format("Статус %s уже существует", status));
	}
}
