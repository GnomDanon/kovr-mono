package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Элемент заказа")
public class OrderItemDto {

	@Schema(description = "Идентификатор элемента заказа")
	private Long id;

	@Schema(description = "Вид элемента")
	private String productType;

	@Schema(description = "Высота ворса")
	private String pileHeight;

	@Schema(description = "Комментарий")
	private String comment;

	@Schema(description = "Список услуг")
	private OrderItemServiceDto[] services;
}

