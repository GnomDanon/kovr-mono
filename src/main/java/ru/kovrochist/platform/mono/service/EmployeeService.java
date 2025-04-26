package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.employee.CreateEmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.UpdateEmployeeDto;
import ru.kovrochist.platform.mono.dto.employee.role.RoleDto;
import ru.kovrochist.platform.mono.entity.Employee;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.employee.EmployeeMapper;
import ru.kovrochist.platform.mono.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final RoleService roleService;

	public EmployeeDto create(CreateEmployeeDto employeeDto) throws DoesNotExistException {
		RoleDto role = roleService.getById(employeeDto.getRoleId());

		Employee employee = employeeRepository.save(EmployeeMapper.map(employeeDto).setRole(EmployeeMapper.map(role)));
		return EmployeeMapper.map(employee);
	}

	public EmployeeDto update(UpdateEmployeeDto employeeDto) throws DoesNotExistException {
		RoleDto role = roleService.getById(employeeDto.getRoleId());

		Employee employee = employeeRepository.findById(employeeDto.getId()).orElseThrow(() -> new EmployeeDoesNotExistException(employeeDto.getId()));
		employeeRepository.save(employee.setName(employeeDto.getName()).setSurname(employeeDto.getSurname()).setRole(EmployeeMapper.map(role)));

		return EmployeeMapper.map(employee);
	}

	public EmployeeDto getById(UUID id) throws DoesNotExistException {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeDoesNotExistException(id));
		return EmployeeMapper.map(employee);
	}

	public List<EmployeeDto> getByRoleId(UUID roleId) {
		Iterable<Employee> employees = employeeRepository.findAllByRoleId(roleId);
		List<EmployeeDto> result = new ArrayList<>();

		for (Employee employee : employees) {
			result.add(EmployeeMapper.map(employee));
		}

		return result;
	}

	public List<EmployeeDto> get() {
		Iterable<Employee> employees = employeeRepository.findAll();
		List<EmployeeDto> result = new ArrayList<>();

		for (Employee employee : employees) {
			result.add(EmployeeMapper.map(employee));
		}

		return result;
	}
}
