package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.ResourceConflictException;

public class ServiceAlreadyExistsException extends ResourceConflictException {

	public ServiceAlreadyExistsException(String service) {
		super(String.format("Услуга %s уже существует", service));
	}
}
