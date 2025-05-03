package ru.kovrochist.platform.mono.type.mutable;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum District {
	SOUTHWEST(District._SOUTHWEST_),
	CENTRE(District._CENTRE_),
	FILLET(District._FILLET_),
	FRIENDSHIP(District._FRIENDSHIP_),
	COMINTERN(District._COMINTERN_),
	LEPSE(District._LEPSE_),
	ZONAL(District._ZONAL_),
	CHISTYE_PRUDY(District._CHISTYE_PRUDY_),
	NOVOVYATSK(District._NOVOVYATSK_),
	RAINBOW(District._RAINBOW_),
	KIROVO_CHEPETSK(District._KIROVO_CHEPETSK_),
	KOTELNICH(District._KOTELNICH_),
	EUROPEAN_STREETS(District._EUROPEAN_STREETS_),
	ORICHI(District._ORICHI_);

	private static final String _SOUTHWEST_ = "Юго-Запад";
	private static final String _CENTRE_ = "Центр";
	private static final String _FILLET_ = "Филейкад";
	private static final String _FRIENDSHIP_ = "Дружба";
	private static final String _COMINTERN_ = "Коминтерн";
	private static final String _LEPSE_ = "Лепсе";
	private static final String _ZONAL_ = "Зональный";
	private static final String _CHISTYE_PRUDY_ = "Чистые пруды";
	private static final String _NOVOVYATSK_ = "Нововятск";
	private static final String _RAINBOW_ = "Радужный";
	private static final String _KIROVO_CHEPETSK_ = "Кирово-Чепецк";
	private static final String _KOTELNICH_ = "Котельнич";
	private static final String _EUROPEAN_STREETS_ = "Европейские улочки";
	private static final String _ORICHI_ = "Оричи";

	private final String label;

	District(String label) {
		this.label = label;
	}

	public static District byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _SOUTHWEST_ -> SOUTHWEST;
			case _CENTRE_ -> CENTRE;
			case _FILLET_ -> FILLET;
			case _FRIENDSHIP_ -> FRIENDSHIP;
			case _COMINTERN_ -> COMINTERN;
			case _LEPSE_ -> LEPSE;
			case _ZONAL_ -> ZONAL;
			case _CHISTYE_PRUDY_ -> CHISTYE_PRUDY;
			case _NOVOVYATSK_ -> NOVOVYATSK;
			case _RAINBOW_ -> RAINBOW;
			case _KIROVO_CHEPETSK_ -> KIROVO_CHEPETSK;
			case _KOTELNICH_ -> KOTELNICH;
			case _EUROPEAN_STREETS_ -> EUROPEAN_STREETS;
			case _ORICHI_ -> ORICHI;
			default -> throw new DoesNotExistException("Не существует района " + label);
		};
	}
}
