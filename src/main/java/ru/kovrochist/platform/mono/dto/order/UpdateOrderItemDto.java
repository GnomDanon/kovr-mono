package ru.kovrochist.platform.mono.dto.order;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateOrderItemDto {

	private Long itemId;

	private String[] services;
}
