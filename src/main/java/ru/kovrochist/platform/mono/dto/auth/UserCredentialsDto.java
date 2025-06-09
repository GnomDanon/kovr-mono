package ru.kovrochist.platform.mono.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Запрос на подтверждение кода доступа")
public class UserCredentialsDto {

	@Schema(description = "Номер телефона")
	private String tempPhone;

	@Schema(description = "Код доступа")
	private String code;
}
