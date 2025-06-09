package ru.kovrochist.platform.mono.dto.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtAuthenticationDto {

	private String accessToken;

	private String refreshToken;
}
