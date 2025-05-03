package ru.kovrochist.platform.mono.type.mutable;

import lombok.Getter;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;

@Getter
public enum Source {
	WEBSITE(Source._WEBSITE_),
	AVITO(Source._AVITO_),
	SOCIAL(Source._SOCIAL_),
	MAPS(Source._MAPS_),
	MESSENGERS(Source._MESSENGERS_),
	RECOMMENDATIONS(Source._RECOMMENDATIONS_),
	OFFLINE(Source._OFFLINE_);

	private static final String _WEBSITE_ = "Сайт (Яндекс директ, Яндекс бизнес)";
	private static final String _AVITO_ = "Авито";
	private static final String _SOCIAL_ = "Соц.сети (ВК, Инстаграм, Тг-канал)";
	private static final String _MAPS_ = "Карты (Яндекс и 2GIS)";
	private static final String _MESSENGERS_ = "Мессенджеры (WhatsApp)";
	private static final String _RECOMMENDATIONS_ = "Рекомендации знакомых";
	private static final String _OFFLINE_ = "Офлайн (Баннеры, листовки, флаеры, наклейки на подъезд)";

	private final String label;

	Source(String label) {
		this.label = label;
	}

	public static Source byLabel(String label) throws DoesNotExistException {
		return switch (label) {
			case _WEBSITE_ -> WEBSITE;
			case _AVITO_ -> AVITO;
			case _SOCIAL_ -> SOCIAL;
			case _MAPS_ -> MAPS;
			case _MESSENGERS_ -> MESSENGERS;
			case _RECOMMENDATIONS_ -> RECOMMENDATIONS;
			case _OFFLINE_ -> OFFLINE;
			default -> throw new DoesNotExistException("Не существует источника " + label);
		};
	}
}
