package ru.kovrochist.platform.mono.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Роль пользователя")
@AllArgsConstructor
public class RoleDto {

	@Schema(description = "Значение роли")
	private String value;
}
