package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Обертка вокруг статуса заказа")
public class StatusWrapper {

	@Schema(description = "Статус заказа")
	private String status;
}
