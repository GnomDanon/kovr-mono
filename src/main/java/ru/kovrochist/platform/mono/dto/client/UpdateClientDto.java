package ru.kovrochist.platform.mono.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Обновление информации о клиенте")
public class UpdateClientDto {

	@Schema(description = "Идентификатор клиента")
	private UUID id;

	@Schema(description = "Имя клиента")
	private String name;

	@Schema(description = "Комментарий")
	private String comment;

	@Schema(description = "Идентификатор статуса клиента")
	private UUID clientStatusId;
}
