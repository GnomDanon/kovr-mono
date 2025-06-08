package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.user.RoleWrapper;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.filter.EmployeeFilter;
import ru.kovrochist.platform.mono.mapper.employee.EmployeeMapper;
import ru.kovrochist.platform.mono.repository.EmployeeRepository;
import ru.kovrochist.platform.mono.type.Gender;
import ru.kovrochist.platform.mono.type.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public List<EmployeeDto> getEmployees() {
		List<Employees> employees = get();
		return employees.stream().map(EmployeeMapper::map).collect(Collectors.toList());
	}

	public EmployeeDto getEmployee(Long id) throws EmployeeDoesNotExistException {
		Employees employee = getById(id);
		return EmployeeMapper.map(employee);
	}

	public List<EmployeeDto> getEmployees(EmployeeFilter filter) {
		List<Employees> employees = get(filter);
		return employees.stream().map(EmployeeMapper::map).collect(Collectors.toList());
	}

	public EmployeeDto createEmployee(UserDto userDto) throws DoesNotExistException, EmployeeAlreadyExistsException {
		Employees employee = create(userDto.getFirstName(), userDto.getLastName(), userDto.getBirthday(), userDto.getPhone(), Role.byLabel(userDto.getRole()), userDto.getGender() == null ? null : Gender.byLabel(userDto.getGender()));
		return EmployeeMapper.map(employee);
	}

	public EmployeeDto updateEmployeeRole(Long id, RoleWrapper role) throws DoesNotExistException {
		Employees employee = getById(id);
		employeeRepository.save(employee.setRole(Role.byLabel(role.getValue())));
		return EmployeeMapper.map(employee);
	}

	protected Employees getById(Long id) throws EmployeeDoesNotExistException {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeDoesNotExistException(id));
	}

	protected Employees getByPhone(String phone) {
		return employeeRepository.findByPhone(phone).orElse(null);
	}

	private List<Employees> get() {
		List<Employees> result = new ArrayList<>();
		Iterable<Employees> employees = employeeRepository.findAll();

		for (Employees employee : employees) {
			result.add(employee);
		}

		return result;
	}

	private List<Employees> get(EmployeeFilter filter) {
		List<Employees> result = new ArrayList<>();
		Iterable<Employees> employees = employeeRepository.find(filter.getSearch(), filter.getStatus(), filter.getOnShift(), filter.getRole());

		for (Employees employee : employees) {
			result.add(employee);
		}

		return result;
	}

	private Employees create(String firstName, String lastName, Date birthday, String phone, Role role, Gender gender) throws EmployeeAlreadyExistsException {
		Employees employee = employeeRepository.findByPhone(phone).orElse(null);

		if (employee != null) {
			throw new EmployeeAlreadyExistsException(phone);
		}

		employee = new Employees().setFirstName(firstName).setLastName(lastName).setBirthday(birthday).setPhone(phone).setRole(role).setGender(gender);
		return employeeRepository.save(employee.setCreatedAt(new Date()));
	}

	protected Employees update(Long id, String firstName, String lastName, Date birthday, String phone, String gender) throws DoesNotExistException {
		Employees employee = getById(id);
		return update(employee, firstName, lastName, birthday, phone, employee.getRole().getLabel(), gender);
	}

	private Employees update(Employees employee, String firstName, String lastName, Date birthday, String phone, String roleValue, String genderLabel) throws DoesNotExistException {
		Role role = Role.byLabel(roleValue);
		Gender gender = genderLabel == null ? null : Gender.byLabel(genderLabel);
		Date employeeBirthday = employee.getBirthday();
		if (employeeBirthday != null)
			birthday = employeeBirthday;
		return update(employee, firstName, lastName, birthday, phone, role, gender);
	}

	private Employees update(Employees employees, String firstName, String lastName, Date birthday, String phone, Role role, Gender gender) {
		return employeeRepository.save(employees.setFirstName(firstName).setLastName(lastName).setBirthday(birthday).setPhone(phone).setRole(role).setGender(gender));
	}

	protected void setCode(Employees employee, String code) {
		employeeRepository.save(employee.setCode(code));
	}
}
