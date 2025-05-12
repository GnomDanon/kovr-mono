package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

public class GenderDoesNotExistsException extends DoesNotExistException {

	public GenderDoesNotExistsException(String label) {
		super("Не существует гендера: " + label);
	}
}
