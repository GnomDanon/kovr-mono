package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.metadata.DeliveryTypeDoesNotExistsException;

@Getter
public enum DeliveryType implements LabeledEnum {
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
		return LabeledEnum.byLabel(DeliveryType.class, label, DeliveryTypeDoesNotExistsException::new);
	}
}
