package ru.kovrochist.platform.mono.exception.employee;

import ru.kovrochist.platform.mono.exception.DoesNotExistException;

import java.util.UUID;

public class EmployeeOrderItemDoesNotExistsException extends DoesNotExistException {

	public EmployeeOrderItemDoesNotExistsException(UUID employeeId, UUID orderId) {
		super(String.format("Сотрудник с идентификатором %s не назначен на заказ с идентификатором %s", employeeId.toString(), orderId.toString()));
	}
}
