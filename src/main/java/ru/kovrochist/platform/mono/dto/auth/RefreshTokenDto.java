package ru.kovrochist.platform.mono.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.Description;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Description("Запрос на обновление токена")
public class RefreshTokenDto {

	@Schema(description = "Токен обновления")
	private String refreshToken;
}
