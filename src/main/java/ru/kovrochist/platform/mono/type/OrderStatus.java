package ru.kovrochist.platform.mono.type;

public enum OrderStatus {
	CREATED,
	APPROVED,
	PICKING_UP,
	PICKED_UP,
	IN_WORK,
	READY,
	DELIVERING,
	COMPLETED;

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
