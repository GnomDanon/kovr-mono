package ru.kovrochist.platform.mono.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.kovrochist.platform.mono.dto.employee.EmployeeDto;
import ru.kovrochist.platform.mono.dto.user.RoleWrapper;
import ru.kovrochist.platform.mono.exception.Advice;
import ru.kovrochist.platform.mono.exception.employee.EmployeeAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.filter.EmployeeFilter;
import ru.kovrochist.platform.mono.service.EmployeeService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).setControllerAdvice(new Advice()).build();
	}

	@Test
	public void testGetEmployees() throws Exception {
		EmployeeDto employee1 = new EmployeeDto(1L, "70000000001");
		EmployeeDto employee2 = new EmployeeDto(2L, "70000000002");
		when(employeeService.getEmployees()).thenReturn(List.of(employee1, employee2));

		mockMvc.perform(get("/staff")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].phone").value("70000000001"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].phone").value("70000000002"));
	}

	@Test
	void testGetEmployees_NoEmployees() throws Exception {
		when(employeeService.getEmployees()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/staff"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty());
	}

	@Test
	void testGetEmployeeById_Success() throws Exception {
		EmployeeDto employee = new EmployeeDto(1L, "70000000001");
		when(employeeService.getEmployee(1L)).thenReturn(employee);

		mockMvc.perform(get("/staff/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.phone").value("70000000001"));
	}

	@Test
	void testGetClientById_ClientNotFound() throws Exception {
		when(employeeService.getEmployee(1L)).thenThrow(new EmployeeDoesNotExistException(1L));

		mockMvc.perform(get("/staff/{id}", 1L).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound())
				.andExpect(content().string("Сотрудник с идентификатором 1 не найден"));
	}

	@Test
	public void testCreateEmployee_Success() throws Exception {
		EmployeeDto employeeDto = new EmployeeDto(1L, "70000000001");
		when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);

		mockMvc.perform(post("/staff")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"phone\": \"70000000001\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.phone").value("70000000001"));
	}

	@Test
	void testCreateEmployee_EmployeeAlreadyExists() throws Exception {
		when(employeeService.createEmployee(any(EmployeeDto.class))).thenThrow(new EmployeeAlreadyExistsException("70000000001"));

		mockMvc.perform(post("/staff")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"phone\": \"70000000001\"}")
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isConflict())
				.andExpect(content().string("Сотрудник с номером телефона 70000000001 уже существует"));
	}

	@Test
	public void testUpdateEmployeeRole_Success() throws Exception {
		RoleWrapper roleWrapper = new RoleWrapper("ADMIN");
		EmployeeDto employeeDto = new EmployeeDto(1L, "70000000001");
		when(employeeService.updateEmployeeRole(1L, roleWrapper)).thenReturn(employeeDto);

		mockMvc.perform(patch("/staff/{id}/role", 1L)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"value\": \"ADMIN\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.phone").value("70000000001"));
	}

	@Test
	void testUpdateEmployeeRole_EmployeeNotFound() throws Exception {
		RoleWrapper roleWrapper = new RoleWrapper("ADMIN");
		when(employeeService.updateEmployeeRole(1L, roleWrapper)).thenThrow(new EmployeeDoesNotExistException(1L));

		mockMvc.perform(patch("/staff/{id}/role", 1L)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"value\": \"ADMIN\"}")
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound())
				.andExpect(content().string("Сотрудник с идентификатором 1 не найден"));
	}

	@Test
	void testFetchFilteredEmployees_Success() throws Exception {
		EmployeeFilter filter = new EmployeeFilter(Map.of("search", "John"));
		EmployeeDto employee = new EmployeeDto(1L, "70000000001");
		when(employeeService.getEmployees(any(EmployeeFilter.class))).thenReturn(List.of(employee));

		mockMvc.perform(get("/staff/filter")
						.param("search", "John")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].phone").value("70000000001"));
	}
}
