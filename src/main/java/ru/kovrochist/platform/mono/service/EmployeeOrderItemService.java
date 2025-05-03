package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.entity.EmployeeOrderItems;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeOrderItemDoesNotExistsException;
import ru.kovrochist.platform.mono.repository.EmployeeOrderItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeOrderItemService {

	private final EmployeeOrderItemRepository employeeOrderItemRepository;

	public EmployeeOrderItems create(Orders order, Employees employee) {
		return employeeOrderItemRepository.save(new EmployeeOrderItems().setOrder(order).setEmployee(employee));
	}

	public EmployeeOrderItems get(UUID employeeId, UUID orderId) throws DoesNotExistException {
		return employeeOrderItemRepository.find(employeeId, orderId).orElseThrow(() -> new EmployeeOrderItemDoesNotExistsException(employeeId, orderId));
	}

	public List<Orders> getOrdersByEmployeeId(UUID employeeId) {
		List<Orders> result = new ArrayList<>();
		Iterable<EmployeeOrderItems> items = employeeOrderItemRepository.findByEmployeeId(employeeId);

		for (EmployeeOrderItems item : items) {
			result.add(item.getOrder());
		}

		return result;
	}

	public List<Employees> getEmployeesByOrderId(UUID orderId) {
		List<Employees> result = new ArrayList<>();
		Iterable<EmployeeOrderItems> items = employeeOrderItemRepository.findByOrderId(orderId);

		for (EmployeeOrderItems item : items) {
			result.add(item.getEmployee());
		}

		return result;
	}
}
