package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.order.status.CreateOrderStatusDto;
import ru.kovrochist.platform.mono.dto.order.status.OrderStatusDto;
import ru.kovrochist.platform.mono.entity.OrderStatus;
import ru.kovrochist.platform.mono.mapper.order.OrderMapper;
import ru.kovrochist.platform.mono.repository.OrderStatusRepository;

@Service
@RequiredArgsConstructor
public class OrderStatusService {

	private final OrderStatusRepository orderStatusRepository;

	public OrderStatusDto create(CreateOrderStatusDto orderStatusDto) {
		OrderStatus orderStatus = orderStatusRepository.save(OrderMapper.map(orderStatusDto));
		return OrderMapper.map(orderStatus);
	}
}
