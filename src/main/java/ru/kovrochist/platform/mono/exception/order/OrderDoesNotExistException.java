package ru.kovrochist.platform.mono.exception.order;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

public class OrderDoesNotExistException extends DoesNotExistException {
	public OrderDoesNotExistException(Long id) {
		super(String.format("Заказ с идентификатором %d не найден", id));
	}
}
