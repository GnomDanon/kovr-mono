package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.type.DeliveryDay;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.Date;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Заказ")
public class OrderDto {

	@Schema(description = "Идентификатор заказа")
	private UUID id;

	@Schema(description = "Клиент")
	private ClientDto client;

	@Schema(description = "Статус заказа")
	private OrderStatus status;

	@Schema(description = "Элементы заказа")
	private OrderItemDto[] items;

	@Schema(description = "Тип доставки")
	private DeliveryType deliveryType;

	@Schema(description = "Дата регистрации заказа")
	private Date createdAt;

	@Schema(description = "Дата последнего обновления заказа")
	private Date updatedAt;

	@Schema(description = "Город")
	private String city;

	@Schema(description = "Адрес")
	private String address;

	@Schema(description = "Дни доставки")
	private DeliveryDay[] deliveryDays;

	@Schema(description = "Дата начала доставки")
	private Date deliveryTimeStart;

	@Schema(description = "Дата завершения доставки")
	private Date deliveryTimeEnd;
}
