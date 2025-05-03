package ru.kovrochist.platform.mono.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.type.Role;

@Data
@Accessors(chain = true)
@Schema(description = "Сотрудник")
public class AssignedEmployeeDto {

	@Schema(description = "Идентификатор")
	private Long id;

	@Schema(description = "Роль")
	private Role role;

	@Schema(description = "Полное имя")
	private String fullName;

	@Schema(description = "Комментарий")
	private String comment;
}
