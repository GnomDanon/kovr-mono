package ru.kovrochist.platform.mono.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Запрос на авторизацию")
public class AuthResponseDto {

	@Schema(description = "Номер телефона")
	private String tempPhone;
}
