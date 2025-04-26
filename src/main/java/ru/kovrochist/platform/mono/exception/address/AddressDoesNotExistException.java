package ru.kovrochist.platform.mono.exception.address;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.UUID;

public class AddressDoesNotExistException extends DoesNotExistException {

	public AddressDoesNotExistException(UUID id) {
		super(String.format("Адрес с идентификатором %s не существует", id.toString()));
	}
}
