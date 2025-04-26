package ru.kovrochist.platform.mono.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Адрес")
public class AddressDto {

	@Schema(description = "Идентификатор адреса")
	private UUID id;

	@Schema(description = "Регион")
	private String region;

	@Schema(description = "Город")
	private String city;

	@Schema(description = "Улица")
	private String street;

	@Schema(description = "Номер дома")
	private Integer houseNumber;

	@Schema(description = "Номер квартиры")
	private Integer flatNumber;

	@Schema(description = "Этаж")
	private Integer floorNumber;
}
