package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.ResourceConflictException;

public class DistrictAlreadyExistsException extends ResourceConflictException {

	public DistrictAlreadyExistsException(String district) {
		super(String.format("Район %s уже существует", district));
	}
}
