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
	private Long id;

	@Schema(description = "Имя сотрудника")
	private String firstName;

	@Schema(description = "Отчество сотрудника")
	private String middleName;

	@Schema(description = "Фамилия сотрудника")
	private String lastName;

	@Schema(description = "Роль сотрудника")
	private String role;

	@Schema(description = "Дата рождения")
	private Date birthday;

	@Schema(description = "Номер телефона")
	private String phone;

	@Schema(description = "Почта")
	private String email;
}
