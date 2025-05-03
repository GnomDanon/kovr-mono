package ru.kovrochist.platform.mono.type;

import lombok.Getter;

@Getter
public enum DeliveryType {
	SELF("self", "Самовывоз"),
	DELIVERY("delivery", "Вывоз и доставка"),
	HOME_VISIT("homeVisit", "Выезд на дом");

	private final String value;
	private final String label;

	DeliveryType(String value, String label) {
		this.value = value;
		this.label = label;
	}
}
