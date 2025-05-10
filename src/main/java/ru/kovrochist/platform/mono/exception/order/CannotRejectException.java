package ru.kovrochist.platform.mono.exception.order;

import ru.kovrochist.platform.mono.exception.ResourceConflictException;
import ru.kovrochist.platform.mono.type.OrderStatus;

public class CannotRejectException extends ResourceConflictException {

	public CannotRejectException(Long id, OrderStatus status) {
		super(String.format("Невозможно отменить заказ %d в статусе %s", id, status.toString()));
	}
}
