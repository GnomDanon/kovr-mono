package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.metadata.DeliveryDayDoesNotExistsException;

@Getter
public enum DeliveryDay implements LabeledEnum {
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

	DeliveryDay(String label) {
		this.label = label;
	}

	public static DeliveryDay byLabel(String label) throws DoesNotExistException {
		return LabeledEnum.byLabel(DeliveryDay.class, label, DeliveryDayDoesNotExistsException::new);
	}
}
