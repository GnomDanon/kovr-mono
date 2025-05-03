package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum Gender {
	MALE(Gender._MALE_),
	FEMALE(Gender._FEMALE_);

	private static final String _MALE_ = "Мужской";
	private static final String _FEMALE_ = "Женский";

	private final String label;

	Gender(String label) {
		this.label = label;
	}

	public static Gender byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _MALE_ -> MALE;
			case _FEMALE_ -> FEMALE;
			default -> throw new DoesNotExistException("Не существует пола " + label);
		};
	}
}
