package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.exception.employee.EmployeeAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.repository.EmployeeRepository;
import ru.kovrochist.platform.mono.type.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	private final EmployeeOrderItemService employeeOrderItemService;

	public List<Employees> get() {
		List<Employees> result = new ArrayList<>();
		Iterable<Employees> employees = employeeRepository.findAll();

		for (Employees employee : employees) {
			result.add(employee);
		}

		return result;
	}

	public List<Employees> get(String filter) {
		List<Employees> result = new ArrayList<>();
		Iterable<Employees> employees = employeeRepository.find(filter);

		for (Employees employee : employees) {
			result.add(employee);
		}

		return result;
	}

	public Employees getById(Long id) throws EmployeeDoesNotExistException {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeDoesNotExistException(id));
	}

	public Employees getByPhone(String phone) {
		return employeeRepository.findByPhone(phone).orElse(null);
	}

	public List<Employees> getByOrderId(Long orderId) {
		return employeeOrderItemService.getEmployeesByOrderId(orderId);
	}

	public Employees create(String firstName, String middleName, String lastName, Date birthday, String phone, Role role) throws EmployeeAlreadyExistsException {
		Employees employee = employeeRepository.findByPhone(phone).orElse(null);

		if (employee != null) {
			throw new EmployeeAlreadyExistsException(phone);
		}

		employee = new Employees().setFirstName(firstName).setMiddleName(middleName).setLastName(lastName).setBirthday(birthday).setPhone(phone).setRole(role);
		return employeeRepository.save(employee);
	}

	public Employees update(Long id, String firstName, String middleName, String lastName, Date birthday, Role role) throws EmployeeDoesNotExistException {
		Employees employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeDoesNotExistException(id));
		return employeeRepository.save(employee.setFirstName(firstName).setMiddleName(middleName).setLastName(lastName).setBirthday(birthday).setRole(role));
	}

	public Employees setCode(Employees employee, String code) {
		return employeeRepository.save(employee.setCode(code));
	}
}
