package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Запрос на обновление данных о доставке")
public class RescheduleDto {

	@Schema(description = "Дни доставки")
	private String[] deliveryDays;

	@Schema(description = "Время начала доставки")
	private Date deliveryTimeStart;

	@Schema(description = "Время окончания доставки")
	private Date deliveryTimeEnd;
}
