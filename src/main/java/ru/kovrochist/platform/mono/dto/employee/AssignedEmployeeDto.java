package ru.kovrochist.platform.mono.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Сотрудник")
public class AssignedEmployeeDto {

	@Schema(description = "Идентификатор")
	private Long employeeId;

	@Schema(description = "Роль")
	private String role;

	@Schema(description = "Полное имя")
	private String fullName;

	@Schema(description = "Комментарий")
	private String comment;
}
