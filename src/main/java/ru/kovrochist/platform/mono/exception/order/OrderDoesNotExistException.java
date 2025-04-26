package ru.kovrochist.platform.mono.exception.order;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.UUID;

public class OrderDoesNotExistException extends DoesNotExistException {
	public OrderDoesNotExistException(UUID id) {
		super(String.format("Заказ с идентификатором %s не найден", id.toString()));
	}
}
