package ru.kovrochist.platform.mono.type;

import lombok.Getter;

@Getter
public enum OrderStatus {
	REJECTED("rejected", "Отклонен"),
	CREATED("created", "Заявка"),
	APPROVED("approved", "Одобрено"),
	PICKING_UP("pickingUp", "Забирается"),
	PICKED_UP("pickedUp", "Доставлено в цех"),
	IN_WORK("inWork", "В работе"),
	READY("ready", "Готово"),
	DELIVERING("delivering", "Доставляется"),
	COMPLETED("completed", "Завершен");

	private final String value;
	private final String label;

	OrderStatus(String value, String label) {
		this.value = value;
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
}
