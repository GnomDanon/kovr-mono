package ru.kovrochist.platform.mono.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@Schema(description = "Профиль пользователя")
public class ProfileFormData {

	@Schema(description = "Имя")
	private String firstName;

	@Schema(description = "Фамилия")
	private String lastName;

	@Schema(description = "Номер телефона")
	private String phone;

	@Schema(description = "Дата рождения")
	private Date birthday;

	@Schema(description = "Пол клиента")
	private String gender;

	@Schema(description = "Город")
	private String city;

	@Schema(description = "Адрес")
	private String address;
}
