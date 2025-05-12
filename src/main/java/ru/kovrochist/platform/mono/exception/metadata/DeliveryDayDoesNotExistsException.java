package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

public class DeliveryDayDoesNotExistsException extends DoesNotExistException {

	public DeliveryDayDoesNotExistsException(String label) {
		super("Не существует дня: " + label);
	}
}
