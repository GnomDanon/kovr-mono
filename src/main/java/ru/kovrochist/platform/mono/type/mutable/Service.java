package ru.kovrochist.platform.mono.type.mutable;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum Service {
	DRY_CLEANING(Service._DRY_CLEANING_),
	OVERLOCK(Service._OVERLOCK_),
	ANTIBACTERIAL(Service._ANTIBACTERIAL_),
	DISINFECTION(Service._DISINFECTION_);

	private static final String _DRY_CLEANING_ = "Химчистка";
	private static final String _OVERLOCK_ = "Оверлок";
	private static final String _ANTIBACTERIAL_ = "Антибактериальная обработка";
	private static final String _DISINFECTION_ = "Дезинфекция";

	private final String label;

	Service(String label) {
		this.label = label;
	}

	public static Service byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _DRY_CLEANING_ -> DRY_CLEANING;
			case _OVERLOCK_ -> OVERLOCK;
			case _ANTIBACTERIAL_ -> ANTIBACTERIAL;
			case _DISINFECTION_ -> DISINFECTION;
			default -> throw new DoesNotExistException("Не существует услуги " + label);
		};
	}
}
