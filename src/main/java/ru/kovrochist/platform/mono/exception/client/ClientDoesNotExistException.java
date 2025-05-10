package ru.kovrochist.platform.mono.exception.client;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

public class ClientDoesNotExistException extends DoesNotExistException {
	public ClientDoesNotExistException(Long id) {
		super(String.format("Клиент с идентификатором %d не найден", id));
	}
}
