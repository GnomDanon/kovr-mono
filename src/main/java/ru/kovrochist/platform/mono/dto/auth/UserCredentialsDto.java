package ru.kovrochist.platform.mono.dto.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserCredentialsDto {

	private String tempPhone;

	private String code;
}
