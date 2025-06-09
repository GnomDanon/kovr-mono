package ru.kovrochist.platform.mono.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Токены")
public class JwtAuthenticationDto {

	@Schema(description = "Токен доступа")
	private String accessToken;

	@Schema(description = "Токен обновления")
	private String refreshToken;
}
