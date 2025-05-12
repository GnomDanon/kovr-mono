package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum DeliveryDay {
	MONDAY(DeliveryDay._MONDAY_),
	TUESDAY(DeliveryDay._TUESDAY_),
	WEDNESDAY(DeliveryDay._WEDNESDAY_),
	THURSDAY(DeliveryDay._THURSDAY_),
	FRIDAY(DeliveryDay._FRIDAY_),
	SATURDAY(DeliveryDay._SATURDAY_),
	SUNDAY(DeliveryDay._SUNDAY_);

	private static final String _MONDAY_ = "Пн";
	private static final String _TUESDAY_ = "Вт";
	private static final String _WEDNESDAY_ = "Ср";
	private static final String _THURSDAY_ = "Чт";
	private static final String _FRIDAY_ = "Пт";
	private static final String _SATURDAY_ = "Сб";
	private static final String _SUNDAY_ = "Вс";

	private final String label;

	DeliveryDay(String label) {;
		this.label = label;
	}

	public static DeliveryDay byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _MONDAY_ -> MONDAY;
			case _TUESDAY_ -> TUESDAY;
			case _WEDNESDAY_ -> WEDNESDAY;
			case _THURSDAY_ -> THURSDAY;
			case _FRIDAY_ -> FRIDAY;
			case _SATURDAY_ -> SATURDAY;
			case _SUNDAY_ -> SUNDAY;
			default -> throw new DoesNotExistException("Не существует дня " + label);
		};
	}
}
