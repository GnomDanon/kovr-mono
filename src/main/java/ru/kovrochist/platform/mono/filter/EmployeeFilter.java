package ru.kovrochist.platform.mono.filter;

import lombok.Getter;
import ru.kovrochist.platform.mono.type.Role;
import ru.kovrochist.platform.mono.util.StringUtil;

import java.util.Map;

@Getter
public class EmployeeFilter extends Filter {

	public static final String STATUS = "status";
	public static final String ON_SHIFT = "onShift";
	public static final String ROLE = "role";

	public static final String TRUE = "true";
	public static final String FALSE = "false";

	private final String[] status;
	private final Boolean onShift;
	private final Role[] role;

	public EmployeeFilter(Map<String, String> params) {
		super(params);

		String[] status = params.get(STATUS) == null ? new String[]{} : params.get(STATUS).split(StringUtil.SEPARATOR);
		String onShift = params.get(ON_SHIFT);
		String[] role = params.get(ROLE) == null ? new String[]{} : params.get(ROLE).split(StringUtil.SEPARATOR);

		Role[] roles = new Role[role.length];

		for (int i = 0; i < roles.length; i++) {
			try {
				roles[i] = Role.byLabel(role[i]);
			} catch (Exception ignored) {
			}
		}

		this.status = validate(status) ? status : null;
		this.onShift = onShift == null ? null : onShift.equals(TRUE) ? Boolean.TRUE : onShift.equals(FALSE) ? Boolean.FALSE : null;
		this.role = validate(roles) ? roles : null;
	}
}
