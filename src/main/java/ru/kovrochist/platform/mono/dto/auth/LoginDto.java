package ru.kovrochist.platform.mono.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.util.validation.Phone;

@Data
@Accessors(chain = true)
@Schema(description = "Запрос на авторизацию")
public class LoginDto {

	@Schema(description = "Номер телефона")
	@Phone
	private String tempPhone;
}
