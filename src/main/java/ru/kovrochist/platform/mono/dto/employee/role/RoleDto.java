package ru.kovrochist.platform.mono.dto.employee.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Роль сотрудника")
public class RoleDto {

	@Schema(description = "Идентификатор роли")
	private UUID id;

	@Schema(description = "Название роли")
	private String name;
}
