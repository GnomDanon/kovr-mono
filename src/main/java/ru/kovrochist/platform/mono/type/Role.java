package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum Role {
	GUEST(Role._GUEST_),
	CLIENT(Role._CLIENT_),
	OPERATOR(Role._OPERATOR_),
	COURIER(Role._CLIENT_),
	MASTER(Role._MASTER_),
	ADMIN(Role._ADMIN_);

	private static final String _GUEST_ = "Гость";
	private static final String _CLIENT_ = "Клиент";
	private static final String _OPERATOR_ = "Оператор";
	private static final String _COURIER_ = "Курьер";
	private static final String _MASTER_ = "Мастер";
	private static final String _ADMIN_ = "Администратор";


	private final String label;

	Role(String label) {
		this.label = label;
	}

	public static Role[] getEmployeeRoles() {
		return new Role[] { OPERATOR, COURIER, MASTER, ADMIN };
	}

	public static Role[] getClientRoles() {
		return new Role[] { GUEST, CLIENT };
	}

	public static Role byLabel(String label) throws DoesNotExistException{
		return switch (label) {
			case _GUEST_ -> GUEST;
			case _CLIENT_ -> CLIENT;
			case _OPERATOR_ -> OPERATOR;
			case _COURIER_ -> COURIER;
			case _MASTER_ -> MASTER;
			case _ADMIN_ -> ADMIN;
			default -> throw new DoesNotExistException("Не существует роли " + label);
		};
	}
}
