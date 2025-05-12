package ru.kovrochist.platform.mono.filter;

import lombok.Getter;

import java.util.Map;

@Getter
public abstract class Filter {

	public static final String SEARCH = "search";
	public static final String SORT_BY = "sortBy";
	public static final String ORDER_BY = "orderBy";

	private final String search;

	private final String sortBy;
	private final Order orderBy;

	public Filter(Map<String, String> params) {
		String search = params.get(SEARCH);
		String sortBy = params.get(SORT_BY);
		String orderBy = params.get(SORT_BY);

		this.search = search == null ? "%" : "%" + search + "%";
		this.sortBy = sortBy == null ? "" : sortBy;
		this.orderBy = Order.byValue(orderBy);
	}
}
