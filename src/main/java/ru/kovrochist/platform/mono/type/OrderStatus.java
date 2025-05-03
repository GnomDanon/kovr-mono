package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum OrderStatus {
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

	public OrderStatus getNextStatus() {
		int index = this.ordinal() + 1;
		OrderStatus[] statuses = OrderStatus.values();
		if (index == statuses.length) {
			return this;
		}
		return statuses[index];
	}

	public boolean canBeRejected() {
		return this != COMPLETED;
	}

	public static OrderStatus byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _REJECTED_ -> REJECTED;
			case _CREATED_ -> CREATED;
			case _APPROVED_ -> APPROVED;
			case _PICKING_UP_ -> PICKING_UP;
			case _PICKED_UP_ -> PICKED_UP;
			case _IN_WORK_ -> IN_WORK;
			case _READY_ -> READY;
			case _DELIVERING_ -> DELIVERING;
			case _COMPLETED_ -> COMPLETED;
			default -> throw new DoesNotExistException("Не существует статуса заказа " + label);
		};
	}
}
