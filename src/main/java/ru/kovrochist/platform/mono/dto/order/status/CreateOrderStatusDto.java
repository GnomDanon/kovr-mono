package ru.kovrochist.platform.mono.dto.order.status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Создание статуса заказа")
public class CreateOrderStatusDto {

	@Schema(description = "Название статуса заказа")
	private String name;
}
