package ru.kovrochist.platform.mono.filter;

import lombok.Getter;
import ru.kovrochist.platform.mono.util.StringUtil;

import java.util.Map;

@Getter
public class ClientFilter extends Filter {

	public static final String STATUS = "status";
	public static final String DISTRICT = "district";

	private final String[] status;
	private final String[] district;

	public ClientFilter(Map<String, String> params) {
		super(params);

		String[] status = params.get(STATUS) == null ? new String[]{} : params.get(STATUS).split(StringUtil.SEPARATOR);
		String[] district = params.get(DISTRICT) == null ? new String[]{} : params.get(DISTRICT).split(StringUtil.SEPARATOR);

		this.status = validate(status) ? status : null;
		this.district = validate(district) ? district : null;
	}
}
