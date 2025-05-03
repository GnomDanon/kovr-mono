package ru.kovrochist.platform.mono.type.mutable;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum Product {
	WOOL(Product._WOOL_),
	SYNTHETIC(Product._SYNTHETIC_),
	CARPET(Product._CARPET_),
	CARPET_TILE(Product._CARPET_TILE_),
	VISCOSE(Product._VISCOSE_);

	private static final String _WOOL_ = "Шерстяной";
	private static final String _SYNTHETIC_ = "Синтетический";
	private static final String _CARPET_ = "Ковролин";
	private static final String _CARPET_TILE_ = "Ковровая плитка";
	private static final String _VISCOSE_ = "Вискоза";

	private final String label;

	Product(String label) {
		this.label = label;
	}

	public static Product byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _WOOL_ -> WOOL;
			case _SYNTHETIC_ -> SYNTHETIC;
			case _CARPET_ -> CARPET;
			case _CARPET_TILE_ -> CARPET_TILE;
			case _VISCOSE_ -> VISCOSE;
			default -> throw new DoesNotExistException("Не существует типа изделия " + label);
		};
	}
}
