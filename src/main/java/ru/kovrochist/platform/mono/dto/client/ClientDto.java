package ru.kovrochist.platform.mono.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Клиент")
public class ClientDto {

	@Schema(description = "Идентификатор клиента")
	private UUID id;

	@Schema(description = "Имя клиента")
	private String name;

	@Schema(description = "Номер телефона клиента")
	private String phoneNumber;

	@Schema(description = "Комментарий")
	private String comment;

	@Schema(description = "Идентификатор адреса клиента")
	private UUID addressId;

	@Schema(description = "Идентификатор статуса клиента")
	private UUID clientStatusId;
}
