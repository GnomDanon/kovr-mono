package ru.kovrochist.platform.mono.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Заявка")
public class RequestDto {

	@Schema(description = "Идентификатор заявки")
	private Long id;

	@Schema(description = "Номер телефона")
	private String phone;

	@Schema(description = "Имя")
	private String firstName;

	@Schema(description = "Фамилия")
	private String lastName;

	@Schema(description = "Город")
	private String city;

	@Schema(description = "Адрес")
	private String address;

	@Schema(description = "Комментарий")
	private String comment;
}
