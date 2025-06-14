package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.entity.AssignedEmployees;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeOrderItemDoesNotExistsException;
import ru.kovrochist.platform.mono.repository.AssignedEmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignedEmployeeService {

	private final AssignedEmployeeRepository assignedEmployeeRepository;

	protected List<AssignedEmployees> create(Orders order, Employees employee) {
		List<AssignedEmployees> employees = getAssignedByOrderId(order.getId());
		AssignedEmployees assignedEmployee = get(order, employee);
		if (assignedEmployee != null) {
			employees.add(assignedEmployee);
			return employees;
		}
		employees.add(assignedEmployeeRepository.save(new AssignedEmployees().setOrder(order).setEmployee(employee)));
		return employees;
	}

	protected List<Orders> getOrdersByEmployeeId(Long employeeId) {
		List<Orders> result = new ArrayList<>();
		Iterable<AssignedEmployees> items = assignedEmployeeRepository.findByEmployeeId(employeeId);

		for (AssignedEmployees item : items) {
			result.add(item.getOrder());
		}

		return result;
	}

	protected void remove(Long orderId, Long employeeId) {
		AssignedEmployees employee;
		try {
			employee = get(employeeId, orderId);
		} catch (DoesNotExistException e) {
			return;
		}
		assignedEmployeeRepository.delete(employee);
	}

	protected AssignedEmployees updateComment(Long orderId, Long employeeId, String comment) throws DoesNotExistException {
		AssignedEmployees employee = get(employeeId, orderId);
		return assignedEmployeeRepository.save(employee.setComment(comment));
	}

	private AssignedEmployees get(Long employeeId, Long orderId) throws DoesNotExistException {
		return assignedEmployeeRepository.find(employeeId, orderId).orElseThrow(() -> new EmployeeOrderItemDoesNotExistsException(employeeId, orderId));
	}

	private AssignedEmployees get(Orders order, Employees employee) {
		return assignedEmployeeRepository.find(employee.getId(), order.getId()).orElse(null);
	}

	private List<AssignedEmployees> getAssignedByOrderId(Long orderId) {
		List<AssignedEmployees> result = new ArrayList<>();
		Iterable<AssignedEmployees> items = assignedEmployeeRepository.findByOrderId(orderId);

		for (AssignedEmployees item : items) {
			result.add(item);
		}

		return result;
	}
}
