package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.request.RequestDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.ResourceConflictException;
import ru.kovrochist.platform.mono.exception.order.CannotRejectException;
import ru.kovrochist.platform.mono.exception.order.OrderDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.request.RequestMapper;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestService {

	private final OrderService orderService;
	private final ClientService clientService;

	public Orders create(String phone, String firstName, String lastName, String city, String address, String comment) {
		Clients client = clientService.getByPhone(phone);

		if (client == null)
			client = clientService.create(phone, firstName, lastName, city, address);

		return orderService.create(client, city, address, comment);
	}

	public Orders update(RequestDto request) throws OrderDoesNotExistException {
		return orderService.update(request.getId(), request.getCity(), request.getAddress(), request.getComment());
	}
}
