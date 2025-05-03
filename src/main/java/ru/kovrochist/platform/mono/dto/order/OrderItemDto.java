package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Элемент заказа")
public class OrderItemDto {

	@Schema(description = "Идентификатор")
	private Long id;

	@Schema(description = "Вид")
	private String productType;

	@Schema(description = "Площадь")
	private Double area;

	@Schema(description = "Комментарий")
	private String comment;

	@Schema(description = "Услуги")
	private String[] services;

	@Schema(description = "Загрязнения")
	private String[] contaminations;
}

