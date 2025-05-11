package ru.kovrochist.platform.mono.filter;

import lombok.Getter;

@Getter
public enum Order {
	ASC(Order._ASC_),
	DESC(Order._DESC_);

	private static final String _ASC_ = "asc";
	private static final String _DESC_ = "desc";

	private final String value;

	Order(String value) {
		this.value = value;
	}

	public static Order byValue(String value) {
		if (value != null && value.equals(_DESC_))
			return Order.DESC;
		return Order.ASC;
	}
}
