package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.address.AddressDto;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Заказ")
public class CreateOrderDto {

	@Schema(description = "Количество предметов в заказе")
	private Integer itemsCount;

	@Schema(description = "Комментарий к заказу")
	private String comment;

	@Schema(description = "Дополнительные услуги")
	private String additionalService;

	@Schema(description = "Идентификатор клиента")
	private UUID clientId;

	@Schema(description = "Адрес")
	private AddressDto address;
}