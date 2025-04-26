package ru.kovrochist.platform.mono.mapper.employee;

import ru.kovrochist.platform.mono.dto.employee.CreateEmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.role.CreateRoleDto;
import ru.kovrochist.platform.mono.dto.employee.role.RoleDto;
import ru.kovrochist.platform.mono.entity.Employee;
import ru.kovrochist.platform.mono.entity.Role;

public class EmployeeMapper {

	public static Employee map(CreateEmployeeDto employeeDto) {
		return new Employee()
				.setName(employeeDto.getName())
				.setSurname(employeeDto.getSurname())
				.setEmploymentDate(employeeDto.getEmploymentDate());
	}

	public static EmployeeDto map(Employee employee) {
		return new EmployeeDto()
				.setId(employee.getId())
				.setName(employee.getName())
				.setSurname(employee.getSurname())
				.setEmploymentDate(employee.getEmploymentDate())
				.setRoleId(employee.getRole().getId());
	}

	public static Role map(RoleDto roleDto) {
		return new Role().setId(roleDto.getId()).setName(roleDto.getName());
	}

	public static Role map(CreateRoleDto roleDto) {
		return new Role().setName(roleDto.getName());
	}

	public static RoleDto map(Role role) {
		return new RoleDto().setId(role.getId()).setName(role.getName());
	}
}
