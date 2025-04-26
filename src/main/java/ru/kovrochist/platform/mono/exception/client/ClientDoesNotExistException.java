package ru.kovrochist.platform.mono.exception.client;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.UUID;

public class ClientDoesNotExistException extends DoesNotExistException {
	public ClientDoesNotExistException(UUID id) {
		super(String.format("Клиент с идентификатором %s не найден", id.toString()));
	}
}
