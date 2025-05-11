package ru.kovrochist.platform.mono.mapper.employee;

import ru.kovrochist.platform.mono.dto.user.RoleWrapper;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.type.Gender;

public class EmployeeMapper {

	public static UserDto map(Employees employee) {
		return new UserDto()
				.setId(employee.getId())
				.setFirstName(employee.getFirstName())
				.setLastName(employee.getLastName())
				.setBirthday(employee.getBirthday())
				.setPhone(employee.getPhone())
				.setRole(new RoleWrapper().setValue(employee.getRole().getValue()))
				.setGender(employee.getGender() == null ? null : employee.getGender().getLabel());
	}
}
