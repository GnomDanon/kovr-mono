package ru.kovrochist.platform.mono.type.mutable;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum ClientStatus {
	NEW(ClientStatus._NEW_),
	REGULAR(ClientStatus._REGULAR_),
	INTERMITTENT(ClientStatus._INTERMITTENT_);

	private static final String _NEW_ = "Новый";
	private static final String _REGULAR_ = "Постоянный";
	private static final String _INTERMITTENT_ = "Непостоянный";

	private final String label;

	ClientStatus(String label) {
		this.label = label;
	}

	public static ClientStatus byLabel(String label) throws DoesNotExistException{
		return switch (label) {
			case _NEW_ -> NEW;
			case _REGULAR_ -> REGULAR;
			case _INTERMITTENT_ -> INTERMITTENT;
			default -> throw new DoesNotExistException("Не существует статуса клиента " + label);
		};
	}
}
