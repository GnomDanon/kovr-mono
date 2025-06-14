package ru.kovrochist.platform.mono.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.user.UserDto;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "Клиент")
public class ClientDto extends UserDto {

	@Schema(description = "Количество заказов")
	private Integer ordersCount;

	@Schema(description = "Количество дней с последнего заказа")
	private Long daysWithoutOrders;

	@Schema(description = "Статус")
	private String status;

	@Schema(description = "Средняя стоимость заказа")
	private Double averageCheck;

	public ClientDto(Long id, String phone) {
		super(id, phone);
	}
}
