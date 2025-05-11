package ru.kovrochist.platform.mono.filter;

import lombok.Getter;
import ru.kovrochist.platform.mono.type.Role;

import java.util.Map;

@Getter
public class EmployeeFilter extends Filter {

	public static final String STATUS = "status";
	public static final String ON_SHIFT = "onShift";
	public static final String ROLE = "role";

	public static final String TRUE = "true";
	public static final String FALSE = "false";

	private final String status;
	private final Boolean onShift;
	private final Role role;

	public EmployeeFilter(Map<String, String> params) {
		super(params);

		String status = params.get(STATUS);
		String onShift = params.get(ON_SHIFT);
		String role = params.get(ROLE);

		Role employeeRole;

		try {
			employeeRole = Role.byLabel(role);
		} catch (Exception e) {
			employeeRole = null;
		}

		this.status = status == null ? "" : status;
		this.onShift = onShift.equals(TRUE) ? Boolean.TRUE : onShift.equals(FALSE) ? Boolean.FALSE : null;
		this.role = employeeRole;
	}
}
