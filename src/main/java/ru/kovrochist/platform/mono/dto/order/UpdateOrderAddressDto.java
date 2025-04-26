package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.address.CreateAddressDto;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Обновление адреса заказа")
public class UpdateOrderAddressDto {

	@Schema(description = "Идентификатор заказа")
	private UUID id;

	@Schema(description = "Адрес заказа")
	private CreateAddressDto address;
}
