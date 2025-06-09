package ru.kovrochist.platform.mono.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.util.validation.Phone;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserDto {

	@Schema(description = "Идентификатор")
	private Long id;

	@Schema(description = "Номер телефона")
	@Phone
	private String phone;

	@Schema(description = "Роль")
	private String role;

	@Schema(description = "Имя")
	private String firstName;

	@Schema(description = "Фамилия")
	private String lastName;

	@Schema(description = "Дата рождения")
	private Date birthday;

	@Schema(description = "Пол")
	private String gender;

	@Schema(description = "Город")
	private String city;

	@Schema(description = "Адрес")
	private String address;

	@Schema(description = "Дата создания аккаунта")
	private Date createdAt;

	public UserDto(Long id, String phone) {
		this.id = id;
		this.phone = phone;
	}
}
