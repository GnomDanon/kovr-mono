package ru.kovrochist.platform.mono.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.user.RoleWrapper;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.repository.EmployeeRepository;
import ru.kovrochist.platform.mono.type.Gender;
import ru.kovrochist.platform.mono.type.Role;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	private Employees testEmployee;
	private UserDto testUserDto;
	private RoleWrapper testRoleWrapper;

	private final Long testId = 1L;
	private final String testPhone = "+79998887766";
	private final Date testBirthday = new Date();

	@BeforeEach
	void setUp() {
		String testFirstName = "Иван";
		String testLastName = "Иванов";
		String testCode = "123456";
		testEmployee = new Employees()
				.setId(testId)
				.setFirstName(testFirstName)
				.setLastName(testLastName)
				.setBirthday(testBirthday)
				.setPhone(testPhone)
				.setGender(Gender.MALE)
				.setRole(Role.ADMIN)
				.setCode(testCode);

		String testGenderLabel = "Мужской";
		String testRoleLabel = "admin";
		testUserDto = new UserDto()
				.setFirstName(testFirstName)
				.setLastName(testLastName)
				.setBirthday(testBirthday)
				.setPhone(testPhone)
				.setGender(testGenderLabel)
				.setRole(testRoleLabel);

		testRoleWrapper = new RoleWrapper(testRoleLabel);
	}

	@Test
	@DisplayName("Получение сотрудников")
	void getEmployees_ShouldReturnListOfEmployeeDtos() {
		List<Employees> employeesList = Collections.singletonList(testEmployee);
		when(employeeRepository.findAll()).thenReturn(employeesList);

		List<EmployeeDto> result = employeeService.getEmployees();

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(employeeRepository).findAll();
	}

	@Test
	@DisplayName("Получение сотрудников из пустой базы")
	void getEmployees_WhenNoEmployees_ShouldReturnEmptyList() {
		when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

		List<EmployeeDto> result = employeeService.getEmployees();

		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("Получение сотрудника по идентификатору")
	void getEmployee_WithExistingId_ShouldReturnEmployeeDto() throws EmployeeDoesNotExistException {
		when(employeeRepository.findById(testId)).thenReturn(Optional.of(testEmployee));

		EmployeeDto result = employeeService.getEmployee(testId);

		assertNotNull(result);
		verify(employeeRepository).findById(testId);
	}

	@Test
	@DisplayName("Получение сотрудника по несуществующему идентификатору")
	void getEmployee_WithNonExistingId_ShouldThrowException() {
		when(employeeRepository.findById(testId)).thenReturn(Optional.empty());

		assertThrows(EmployeeDoesNotExistException.class, () -> employeeService.getEmployee(testId));
	}

	@Test
	@DisplayName("Создание сотрудника")
	void createEmployee_WithNewPhone_ShouldReturnEmployeeDto() throws DoesNotExistException, EmployeeAlreadyExistsException {
		when(employeeRepository.findByPhone(testPhone)).thenReturn(Optional.empty());
		when(employeeRepository.save(any(Employees.class))).thenReturn(testEmployee);

		EmployeeDto result = employeeService.createEmployee(testUserDto);

		assertNotNull(result);
		verify(employeeRepository).findByPhone(testPhone);
		verify(employeeRepository).save(any(Employees.class));
	}

	@Test
	@DisplayName("Создание сотрудника с существующим номером телефона")
	void createEmployee_WithExistingPhone_ShouldThrowException() {
		when(employeeRepository.findByPhone(testPhone)).thenReturn(Optional.of(testEmployee));

		assertThrows(EmployeeAlreadyExistsException.class, () -> employeeService.createEmployee(testUserDto));
	}

	@Test
	@DisplayName("Обновление роли сотрудника")
	void updateEmployeeRole_WithExistingId_ShouldReturnUpdatedEmployeeDto() throws DoesNotExistException {
		when(employeeRepository.findById(testId)).thenReturn(Optional.of(testEmployee));
		when(employeeRepository.save(any(Employees.class))).thenReturn(testEmployee);

		EmployeeDto result = employeeService.updateEmployeeRole(testId, testRoleWrapper);

		assertNotNull(result);
		verify(employeeRepository).findById(testId);
		verify(employeeRepository).save(any(Employees.class));
	}

	@Test
	@DisplayName("Обновление роли сотрудника по несуществующему идентификатору")
	void updateEmployeeRole_WithNonExistingId_ShouldThrowException() {
		when(employeeRepository.findById(testId)).thenReturn(Optional.empty());

		assertThrows(EmployeeDoesNotExistException.class, () -> employeeService.updateEmployeeRole(testId, testRoleWrapper));
	}
}
