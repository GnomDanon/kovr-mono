package ru.kovrochist.platform.mono.dto.auth;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.user.UserDto;

@Data
@Accessors(chain = true)
public class ConfirmResponse {

	private UserDto user;

	private JwtAuthenticationDto tokens;
}
