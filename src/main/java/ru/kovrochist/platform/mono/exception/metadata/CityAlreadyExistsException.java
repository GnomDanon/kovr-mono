package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.ResourceConflictException;

public class CityAlreadyExistsException extends ResourceConflictException {

	public CityAlreadyExistsException(String city) {
		super(String.format("Город %s уже существует", city));
	}
}
