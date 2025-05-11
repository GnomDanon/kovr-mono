package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.request.RequestDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.order.OrderMapper;
import ru.kovrochist.platform.mono.security.user.User;

@Service
@RequiredArgsConstructor
public class RequestService {

	private final OrderService orderService;
	private final ClientService clientService;

	private final User USER;

	public OrderDto create(RequestDto request) {
		return create(request.getPhone(), request.getFirstName(), request.getLastName(), request.getCity(), request.getAddress(), request.getComment());
	}

	public OrderDto create(String phone, String firstName, String lastName, String city, String address, String comment) {
		Long userId = USER.getId();
		Clients client;
		if (userId != null) {
			try {
				client = clientService.getById(userId);
			} catch (ClientDoesNotExistException e) {
				client = null;
			}
		} else {
			client = clientService.create(phone, firstName, lastName, city, address);
		}
		Orders order = orderService.create(client, phone, city, address, comment);
		return OrderMapper.map(order);
	}
}
