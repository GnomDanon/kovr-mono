package ru.kovrochist.platform.mono.mapper.employee;

import ru.kovrochist.platform.mono.dto.employee.AssignedEmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.entity.AssignedEmployees;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.util.StringUtil;

public class EmployeeMapper {

	public static UserDto mapToUser(Employees employee) {
		return new UserDto()
				.setId(employee.getId())
				.setFirstName(employee.getFirstName())
				.setLastName(employee.getLastName())
				.setBirthday(employee.getBirthday())
				.setPhone(employee.getPhone())
				.setRole(employee.getRole().getLabel())
				.setGender(employee.getGender() == null ? null : employee.getGender().getLabel())
				.setCreatedAt(employee.getCreatedAt());
	}

	public static EmployeeDto map(Employees employee) {
		EmployeeDto employeeDto = new EmployeeDto()
				.setOnShift(employee.getOnShift())
				.setStatus(employee.getStatus());

		employeeDto.setId(employee.getId())
				.setFirstName(employee.getFirstName())
				.setLastName(employee.getLastName())
				.setBirthday(employee.getBirthday())
				.setPhone(employee.getPhone())
				.setRole(employee.getRole().getLabel())
				.setGender(employee.getGender() == null ? null : employee.getGender().getLabel())
				.setCreatedAt(employee.getCreatedAt());

		return employeeDto;
	}

	public static AssignedEmployeeDto mapAssigned(AssignedEmployees assigned) {
		Employees employee = assigned.getEmployee();

		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		boolean isFirstNameNull = StringUtil.isEmpty(firstName);
		boolean isLastNameNull = StringUtil.isEmpty(lastName);

		String fullName = !isFirstNameNull && !isLastNameNull ? firstName + " " + lastName : !isFirstNameNull ? firstName : !isLastNameNull ? lastName : "";

		return new AssignedEmployeeDto()
				.setEmployeeId(employee.getId())
				.setRole(employee.getRole().getLabel())
				.setFullName(fullName)
				.setComment(assigned.getComment());
	}
}
