package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum DeliveryType {
	SELF(DeliveryType._SELF_),
	DELIVERY(DeliveryType._DELIVERY_),
	HOME_VISIT(DeliveryType._HOME_VISIT_);

	private static final String _SELF_ = "Самовывоз";
	private static final String _DELIVERY_ = "Вывоз и доставка";
	private static final String _HOME_VISIT_ = "Выезд на дом";

	private final String label;

	DeliveryType(String label) {
		this.label = label;
	}

	public static DeliveryType byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _SELF_ -> SELF;
			case _DELIVERY_ -> DELIVERY;
			case _HOME_VISIT_ -> HOME_VISIT;
			default -> throw new DoesNotExistException("Не существует типа доставки " + label);
		};
	}
}
