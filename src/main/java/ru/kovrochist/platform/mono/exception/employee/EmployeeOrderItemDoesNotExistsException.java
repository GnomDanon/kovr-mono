package ru.kovrochist.platform.mono.exception.employee;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.UUID;

public class EmployeeOrderItemDoesNotExistsException extends DoesNotExistException {

	public EmployeeOrderItemDoesNotExistsException(Long employeeId, Long orderId) {
		super(String.format("Сотрудник с идентификатором %d не назначен на заказ с идентификатором %d", employeeId, orderId));
	}
}
