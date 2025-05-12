package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

public class OrderStatusDoesNotExistsException extends DoesNotExistException {

	public OrderStatusDoesNotExistsException(String label) {
		super("Не существует статуса: " + label);
	}
}
