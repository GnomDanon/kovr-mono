package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.metadata.OrderStatusDoesNotExistsException;

@Getter
public enum OrderStatus implements LabeledEnum {
	REJECTED(OrderStatus._REJECTED_),
	CREATED(OrderStatus._CREATED_),
	APPROVED(OrderStatus._APPROVED_),
	PICKING_UP(OrderStatus._PICKING_UP_),
	PICKED_UP(OrderStatus._PICKED_UP_),
	IN_WORK(OrderStatus._IN_WORK_),
	READY(OrderStatus._READY_),
	DELIVERING(OrderStatus._DELIVERING_),
	COMPLETED(OrderStatus._COMPLETED_);

	private static final String _REJECTED_ = "Отклонен";
	private static final String _CREATED_ = "Заявка";
	private static final String _APPROVED_ = "Одобрено";
	private static final String _PICKING_UP_ = "Забирается";
	private static final String _PICKED_UP_ = "Доставлено в цех";
	private static final String _IN_WORK_ = "В работе";
	private static final String _READY_ = "Готово";
	private static final String _DELIVERING_ = "Доставляется";
	private static final String _COMPLETED_ = "Завершен";

	private final String label;

	OrderStatus(String label) {
		this.label = label;
	}

	public static OrderStatus byLabel(String label) throws DoesNotExistException {
		return LabeledEnum.byLabel(OrderStatus.class, label, OrderStatusDoesNotExistsException::new);
	}
}
