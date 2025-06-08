package ru.kovrochist.platform.mono.type;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.metadata.RoleDoesNotExistsException;

@Getter
public enum Role implements LabeledEnum {
	GUEST(Role._GUEST_),
	CLIENT(Role._CLIENT_),
	OPERATOR(Role._OPERATOR_),
	COURIER(Role._COURIER_),
	MASTER(Role._MASTER_),
	ADMIN(Role._ADMIN_);

	private static final String _GUEST_ = "guest";
	private static final String _CLIENT_ = "client";
	private static final String _OPERATOR_ = "operator";
	private static final String _COURIER_ = "courier";
	private static final String _MASTER_ = "master";
	private static final String _ADMIN_ = "admin";

	private final String label;

	Role(String label) {
		this.label = label;
	}

	public static Role byLabel(String label) throws DoesNotExistException{
		return LabeledEnum.byLabel(Role.class, label, RoleDoesNotExistsException::new);
	}

	public boolean isEmployee() {
		return this != GUEST && this != CLIENT;
	}

	public boolean isGuest() {
		return this == GUEST;
	}

	public boolean isClient() {
		return this == CLIENT;
	}

	public boolean isOperator() {
		return this == OPERATOR;
	}

	public boolean isAdmin() {
		return this == ADMIN;
	}
}
