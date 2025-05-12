package ru.kovrochist.platform.mono.type;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.function.Function;

public interface LabeledEnum {
	String getLabel();

	static <T extends Enum<T> & LabeledEnum> T byLabel(Class<T> enumClass, String label, Function<String, DoesNotExistException> exceptionSupplier) throws DoesNotExistException {
		for (T constant : enumClass.getEnumConstants()) {
			if (constant.getLabel().equals(label)) {
				return constant;
			}
		}
		throw exceptionSupplier.apply(label);
	}
}
