package ru.kovrochist.platform.mono.security.access;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kovrochist.platform.mono.entity.AssignedEmployees;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.ResourceAccessException;
import ru.kovrochist.platform.mono.security.user.User;
import ru.kovrochist.platform.mono.type.Role;

@Component
@RequiredArgsConstructor
public class AccessFilter {

	private final User USER;

	public void employeeOrSelf(Clients client) throws ResourceAccessException {
		employeeOrSelf(client.getId());
	}

	public void employeeOrSelf(Orders order) throws ResourceAccessException {
		employeeOrSelf(order.getClient().getId());
	}

	public void employeeOrSelf(Long id) throws ResourceAccessException {
		check(USER.getRole().isEmployee() || USER.getId().equals(id));
	}

	public void operatorOrAdminOrAssignee(Orders order) throws ResourceAccessException {
		Role role = USER.getRole();
		check( role.isOperator() || role.isAdmin() || isAssignee(order));
	}

	private boolean isAssignee(Orders order) {
		boolean isAssignee = false;
		for (AssignedEmployees employee : order.getEmployees()) {
			if (USER.getId().equals(employee.getEmployee().getId())) {
				isAssignee = true;
				break;
			}
		}
		return isAssignee;
	}

	private void check(boolean flag) throws ResourceAccessException {
		if (!flag)
			throw new ResourceAccessException();
	}
}
