package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.ResourceConflictException;

public class ContamintaionAlreadyExistsException extends ResourceConflictException {

	public ContamintaionAlreadyExistsException(String contamination) {
		super(String.format("Загрязнение %s уже существует", contamination));
	}
}
