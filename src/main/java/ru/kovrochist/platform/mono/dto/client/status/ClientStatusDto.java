package ru.kovrochist.platform.mono.dto.client.status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Статус клиента")
public class ClientStatusDto {

	@Schema(description = "Идентификатор статуса клиента")
	private UUID id;

	@Schema(description = "Название статуса")
	private String name;
}
