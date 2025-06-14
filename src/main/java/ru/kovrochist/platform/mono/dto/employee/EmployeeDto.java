package ru.kovrochist.platform.mono.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.dto.user.UserDto;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "Сотрудник")
public class EmployeeDto extends UserDto {

	@Schema(description = "На смене")
	private Boolean onShift;

	@Schema(description = "Статус")
	private String status;

	public EmployeeDto(Long id, String phone) {
		super(id, phone);
	}
}
