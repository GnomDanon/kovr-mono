package ru.kovrochist.platform.mono.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.type.Role;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserDto {

	@Schema(description = "Идентификатор")
	private Long id;

	@Schema(description = "Номер телефона")
	private String phone;

	@Schema(description = "Роль")
	private Role role;

	@Schema(description = "Имя")
	private String firstName;

	@Schema(description = "Фамилия")
	private String lastName;

	@Schema(description = "Дата рождения")
	private Date birthday;

	@Schema(description = "Город")
	private String city;

	@Schema(description = "Адрес")
	private String address;

	@Schema(description = "Пол")
	private String gender;
}
