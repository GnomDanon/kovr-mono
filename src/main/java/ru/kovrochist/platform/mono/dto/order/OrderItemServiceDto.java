package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Вид работы для элемента заказа")
public class OrderItemServiceDto {

	@Schema(description = "Идентификатор вида работы для элемента заказа")
	private Long id;

	@Schema(description = "Вид работы")
	private String service;

	@Schema(description = "Стоимость")
	private Double cost;
}

