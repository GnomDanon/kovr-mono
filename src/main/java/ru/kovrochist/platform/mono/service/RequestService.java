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

	public RequestDto create(RequestDto request) {
		Clients client = clientService.getByPhone(request.getPhone());

		if (client == null)
			client = clientService.create(request.getPhone(), request.getFirstName(), request.getLastName(),
					request.getCity(), request.getAddress());

		Orders order = orderService.create(client, request.getCity(), request.getAddress(), request.getComment());
		return RequestMapper.map(order);
	}

	public RequestDto update(RequestDto request) throws OrderDoesNotExistException {
		Orders order = orderService.update(request.getId(), request.getCity(), request.getAddress(), request.getComment());
		return RequestMapper.map(order);
	}
}
