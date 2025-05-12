package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

public class DeliveryTypeDoesNotExistsException extends DoesNotExistException {

	public DeliveryTypeDoesNotExistsException(String label) {
		super("Не существует типа доставки: " + label);
	}
}
