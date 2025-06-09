package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.dto.employee.AssignedEmployeeDto;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "Заказ")
public class OrderDto {

	@Schema(description = "Идентификатор")
	private Long id;

	@Schema(description = "Номер заказа")
	private String orderNumber;

	@Schema(description = "Клиент")
	private ClientDto client;

	@Schema(description = "Статус")
	private String status;

	@Schema(description = "Элементы")
	private OrderItemDto[] items;

	@Schema(description = "Комментарий")
	private String comment;

	@Schema(description = "Тип доставки")
	private String deliveryType;

	@Schema(description = "Дата взятия заказа в работу")
	private Date createdAt;

	@Schema(description = "Номер телефона")
	private String phone;

	@Schema(description = "Город")
	private String city;

	@Schema(description = "Адрес")
	private String address;

	@Schema(description = "Район")
	private String district;

	@Schema(description = "Дни доставки")
	private String[] deliveryDays;

	@Schema(description = "Дата начала доставки")
	private Date deliveryTimeStart;

	@Schema(description = "Дата окончания доставки")
	private Date deliveryTimeEnd;

	@Schema(description = "Размер скидки")
	private Double discount;

	@Schema(description = "Источники")
	private String[] sources;

	@Schema(description = "Стоимость")
	private Integer price;

	@Schema(description = "Назначенные сотрудники")
	private AssignedEmployeeDto[] assignees;

	public OrderDto(Long id) {
		this.id = id;
	}
}
