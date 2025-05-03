package ru.kovrochist.platform.mono.type.mutable;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum Contamination {
	URINE(Contamination._URINE_),
	PLASTICINE(Contamination._PLASTICINE_),
	PAINT_VANISH_GLUE(Contamination._PAINT_VANISH_GLUE_),
	WINE_JAME(Contamination._WINE_JAME_),
	UNKNOWN(Contamination._UNKNOWN_);

	private static final String _URINE_ = "Урина/запах";
	private static final String _PLASTICINE_ = "Пластилин";
	private static final String _PAINT_VANISH_GLUE_ = "Краска/лак/клей";
	private static final String _WINE_JAME_ = "Вино/варенье";
	private static final String _UNKNOWN_ = "Не знаем, но пятно есть";

	private final String label;

	Contamination(String label) {
		this.label = label;
	}

	public static Contamination byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _URINE_ -> URINE;
			case _PLASTICINE_ -> PLASTICINE;
			case _PAINT_VANISH_GLUE_ -> PAINT_VANISH_GLUE;
			case _WINE_JAME_ -> WINE_JAME;
			case _UNKNOWN_ -> UNKNOWN;
			default -> throw new DoesNotExistException("Не существует загрязнения " + label);
		};
	}
}
