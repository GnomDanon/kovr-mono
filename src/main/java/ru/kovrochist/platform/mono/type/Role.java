package ru.kovrochist.platform.mono.type;

import lombok.Getter;

@Getter
public enum Role {
	GUEST("guest", "Гость"),
	CLIENT("client", "Клиент"),
	OPERATOR("operator", "Оператор"),
	COURIER("courier", "Курьер"),
	MASTER("master", "Мастер"),
	ADMIN("admin", "Администратор");

	private final String value;
	private final String label;

	Role(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public static Role[] getEmployeeRoles() {
		return new Role[] { OPERATOR, COURIER, MASTER, ADMIN };
	}

	public static Role[] getClientRoles() {
		return new Role[] { GUEST, CLIENT };
	}
}
