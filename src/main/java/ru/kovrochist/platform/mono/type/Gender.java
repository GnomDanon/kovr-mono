package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.metadata.GenderDoesNotExistsException;

@Getter
public enum Gender implements LabeledEnum {
	MALE(Gender._MALE_),
	FEMALE(Gender._FEMALE_);

	private static final String _MALE_ = "Мужской";
	private static final String _FEMALE_ = "Женский";

	private final String label;

	Gender(String label) {
		this.label = label;
	}

	public static Gender byLabel(String label) throws DoesNotExistException {
		return LabeledEnum.byLabel(Gender.class, label, GenderDoesNotExistsException::new);
	}
}
