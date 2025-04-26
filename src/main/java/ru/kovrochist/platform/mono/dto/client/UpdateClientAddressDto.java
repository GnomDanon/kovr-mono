package ru.kovrochist.platform.mono.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.address.CreateAddressDto;

import java.util.UUID;
@Data
@Accessors(chain = true)
@Schema(description = "Обновление адреса клиента")
public class UpdateClientAddressDto {

	@Schema(description = "Идентификатор клиента")
	private UUID id;

	@Schema(description = "Адрес клиента")
	private CreateAddressDto address;
}
