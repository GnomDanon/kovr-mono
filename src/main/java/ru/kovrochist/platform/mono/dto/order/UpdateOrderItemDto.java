package ru.kovrochist.platform.mono.dto.order;

import lombok.Data;

@Data
public class UpdateOrderItemDto {

	private Long itemId;

	private String[] services;
}
