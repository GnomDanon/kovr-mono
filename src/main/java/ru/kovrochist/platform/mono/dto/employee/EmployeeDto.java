package ru.kovrochist.platform.mono.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Сотрудник")
public class EmployeeDto {

	@Schema(description = "Идентификатор сотрудника")
	private UUID id;

	@Schema(description = "Имя сотрудника")
	private String name;

	@Schema(description = "Фамилия сотрудника")
	private String surname;

	@Schema(description = "Дата трудоустройства")
	private Date employmentDate;

	@Schema(description = "Идентификатор роли сотрудника")
	private UUID roleId;
}
