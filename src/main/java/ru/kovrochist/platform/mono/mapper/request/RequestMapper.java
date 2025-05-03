package ru.kovrochist.platform.mono.mapper.request;

import ru.kovrochist.platform.mono.dto.request.RequestDto;
import ru.kovrochist.platform.mono.entity.Orders;

public class RequestMapper {
	public static RequestDto map(Orders order) {
		return new RequestDto()
				.setId(order.getId())
				.setPhone(order.getClient().getPhone())
				.setFirstName(order.getClient().getFirstName())
				.setLastName(order.getClient().getLastName())
				.setCity(order.getCity())
				.setAddress(order.getAddress())
				.setComment(order.getComment());
	}
}
