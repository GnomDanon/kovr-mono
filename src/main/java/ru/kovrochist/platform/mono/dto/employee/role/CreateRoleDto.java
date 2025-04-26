package ru.kovrochist.platform.mono.dto.employee.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Создание роли сотрудника")
public class CreateRoleDto {

	@Schema(description = "Название роли")
	private String name;
}