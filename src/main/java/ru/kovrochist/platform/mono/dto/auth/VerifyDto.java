package ru.kovrochist.platform.mono.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Запрос на верификацию")
public class VerifyDto {

	@Schema(description = "Номер телефона")
	private String phone;

	@Schema(description = "Код")
	private String code;
}
