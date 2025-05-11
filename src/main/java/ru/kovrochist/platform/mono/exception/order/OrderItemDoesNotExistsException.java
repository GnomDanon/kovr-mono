package ru.kovrochist.platform.mono.exception.order;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

public class OrderItemDoesNotExistsException extends DoesNotExistException {
	public OrderItemDoesNotExistsException(Long id) {
		super(String.format("Элемент с идентификатором %d не найден", id));
	}
}
