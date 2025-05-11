package ru.kovrochist.platform.mono.filter;

import lombok.Getter;

import java.util.Map;

@Getter
public class ClientFilter extends Filter {

	public static final String STATUS = "status";
	public static final String DISTRICT = "district";

	private String status;
	private String district;

	public ClientFilter(Map<String, String> params) {
		super(params);

		String status = params.get(STATUS);
		String district = params.get(DISTRICT);

		this.status = status == null ? "" : status;
		this.district = district == null ? "" : district;
	}

	public ClientFilter setStatus(String status) {
		this.status = "%" + status + "%";
		return this;
	}

	public ClientFilter setDistrict(String district) {
		this.district = "%" + district + "%";
		return this;
	}
}
