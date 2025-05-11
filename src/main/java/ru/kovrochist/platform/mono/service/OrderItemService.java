package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.entity.OrderItems;
import ru.kovrochist.platform.mono.exception.order.OrderItemDoesNotExistsException;
import ru.kovrochist.platform.mono.repository.OrderItemRepository;
import ru.kovrochist.platform.mono.util.StringUtil;

@Service
@RequiredArgsConstructor
public class OrderItemService {

	private final OrderItemRepository orderItemRepository;

	public OrderItems updateServices(Long id, String[] services) throws OrderItemDoesNotExistsException {
		OrderItems item = orderItemRepository.findById(id).orElseThrow(() -> new OrderItemDoesNotExistsException(id));
		return orderItemRepository.save(item.setServices(String.join(StringUtil.SEPARATOR, services)));
	}
}
