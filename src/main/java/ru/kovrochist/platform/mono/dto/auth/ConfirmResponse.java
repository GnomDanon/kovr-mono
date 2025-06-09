package ru.kovrochist.platform.mono.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.user.UserDto;

@Data
@Accessors(chain = true)
@Schema(description = "Ответ на подтверждение кода доступа")
public class ConfirmResponse {

	@Schema(description = "Информация о пользователе")
	private UserDto user;

	@Schema(description = "Токены доступа")
	private JwtAuthenticationDto tokens;
}
