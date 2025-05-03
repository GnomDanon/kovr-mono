package ru.kovrochist.platform.mono.type;

import lombok.Getter;

@Getter
public enum DeliveryDay {
	MONDAY("monday", "Понедельник"),
	TUESDAY("tuesday", "Вторник"),
	WEDNESDAY("wednesday", "Среда"),
	THURSDAY("thursday", "Четверг"),
	FRIDAY("friday", "Пятница"),
	SATURDAY("saturday", "Суббота"),
	SUNDAY("sunday", "Воскресенье");

	private final String value;
	private final String label;

	DeliveryDay(String value, String label) {
		this.value = value;
		this.label = label;
	}
}
