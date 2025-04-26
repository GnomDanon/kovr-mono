package ru.kovrochist.platform.mono.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.address.CreateAddressDto;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Создание клиента")
public class CreateClientDto {

	@Schema(description = "Имя клиента")
	private String name;

	@Schema(description = "Номер телефона клиента")
	private String phoneNumber;

	@Schema(description = "Комментарий")
	private String comment;

	@Schema(description = "Адрес клиента")
	private CreateAddressDto address;

	@Schema(description = "Идентификатор статуса клиента")
	private UUID clientStatusId;
}
