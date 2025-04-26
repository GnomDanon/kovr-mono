package ru.kovrochist.platform.mono.dto.order.status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Статус заказа")
public class OrderStatusDto {

	@Schema(description = "Идентификатор статуса заказа")
	private UUID id;

	@Schema(description = "Название статуса заказа")
	private String name;
}
