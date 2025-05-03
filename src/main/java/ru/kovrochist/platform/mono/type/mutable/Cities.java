package ru.kovrochist.platform.mono.type.mutable;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum Cities {
	KIROV(Cities._KIROV_),
	KIROVO_CHEPETSK(Cities._KIROVO_CHEPETSK_),
	CLOBODSKOY(Cities._CLOBODSKOY_);

	private static final String _KIROV_ = "Киров";
	private static final String _KIROVO_CHEPETSK_ = "Кирово-Чепецк";
	private static final String _CLOBODSKOY_ = "Слободской";

	private final String label;

	Cities(String label) {
		this.label = label;
	}

	public static Cities byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _KIROV_ -> KIROV;
			case _KIROVO_CHEPETSK_ -> KIROVO_CHEPETSK;
			case _CLOBODSKOY_ -> CLOBODSKOY;
			default -> throw new DoesNotExistException("Не существует города " + label);
		};
	}
}
