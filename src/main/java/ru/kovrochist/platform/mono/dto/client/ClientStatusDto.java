package ru.kovrochist.platform.mono.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Статус клиента")
public class ClientStatusDto {

	@Schema(description = "Идентификатор")
	private Long id;

	@Schema(description = "Название")
	private String label;
}
