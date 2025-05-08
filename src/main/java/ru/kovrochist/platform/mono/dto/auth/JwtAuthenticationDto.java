package ru.kovrochist.platform.mono.dto.auth;

import lombok.Data;

@Data
public class JwtAuthenticationDto {

	private String accessToken;

	private String refreshToken;
}
