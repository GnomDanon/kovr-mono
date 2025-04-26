package ru.kovrochist.platform.mono.dto.client.status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Создание статус клиента")
public class CreateClientStatusDto {

	@Schema(description = "Название статуса")
	private String name;
}
