package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Заказ")
public class OrderDto {

	@Schema(description = "Идентификатор заказа")
	private UUID id;

	@Schema(description = "Количество предметов в заказе")
	private Integer itemsCount;

	@Schema(description = "Комментарий к заказу")
	private String comment;

	@Schema(description = "Дополнительные услуги")
	private String additionalService;

	@Schema(description = "Стоимость заказа")
	private Double cost;

	@Schema(description = "Дата регистрации заказа")
	private Date createdAt;

	@Schema(description = "Дата обновления заказа")
	private Date updatedAt;

	@Schema(description = "Идентификатор клиента")
	private UUID clientId;

	@Schema(description = "Идентификатор текущего исполнителя")
	private UUID employeeId;

	@Schema(description = "Идентификатор адреса")
	private UUID addressId;

	@Schema(description = "Идентификатор статуса заказа")
	private UUID orderStatusId;
}
