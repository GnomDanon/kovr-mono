package ru.kovrochist.platform.mono.dto.auth;

import lombok.Data;

@Data
public class UserCredentialsDto {

	private String tempPhone;

	private String code;
}
