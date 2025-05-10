package ru.kovrochist.platform.mono.exception.metadata;

import ru.kovrochist.platform.mono.exception.ResourceConflictException;

public class ProductAlreadyExistsException extends ResourceConflictException {

	public ProductAlreadyExistsException(String product) {
		super(String.format("Изделие %s уже существует", product));
	}
}
