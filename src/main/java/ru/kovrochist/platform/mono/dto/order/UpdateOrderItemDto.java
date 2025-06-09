package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Запрос на обновление элемента заказа")
public class UpdateOrderItemDto {

	@Schema(description = "Идентификатор элемента заказа")
	private Long itemId;

	@Schema(description = "Список услуг")
	private String[] services;
}
