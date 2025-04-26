package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Обновление заказа")
public class UpdateOrderDto {

	@Schema(description = "Идентификатор заказа")
	private UUID id;

	@Schema(description = "Количество предметов в заказе")
	private Integer itemsCount;

	@Schema(description = "Комментарий к заказу")
	private String comment;

	@Schema(description = "Дополнительные услуги")
	private String additionalService;
}
