package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.ResourceConflictException;

public class SourceAlreadyExistsException extends ResourceConflictException {

	public SourceAlreadyExistsException(String source) {
		super(String.format("Источник %s уже существует", source));
	}
}
