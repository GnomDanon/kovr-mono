package ru.kovrochist.platform.mono.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.type.Gender;

import java.util.Date;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Клиент")
public class ClientDto {

	@Schema(description = "Идентификатор клиента")
	private Long id;

	@Schema(description = "Имя клиента")
	private String firstName;

	@Schema(description = "Фамилия клиента")
	private String lastName;

	@Schema(description = "Номер телефона клиента")
	private String phoneNumber;

	@Schema(description = "Город клиента")
	private String city;

	@Schema(description = "Адрес клиента")
	private String address;

	@Schema(description = "Пол")
	private Gender gender;

	@Schema(description = "Дата рождения")
	private Date birthday;

	@Schema(description = "Статус клиента")
	private String status;
}
