package ru.kovrochist.platform.mono.type;

import lombok.Getter;

@Getter
public enum Gender {
	MALE("male", "Мужской"),
	FEMALE("female", "Женский");

	private final String value;
	private final String label;

	Gender(String value, String label) {
		this.value = value;
		this.label = label;
	}
}
