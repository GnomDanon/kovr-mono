package ru.kovrochist.platform.mono.exception.client;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.UUID;

public class ClientStatusDoesNotExistException extends DoesNotExistException {

	public ClientStatusDoesNotExistException(Long id) {
		super(String.format("Статус клиента с идентификатором %d не существует", id));
	}
}
