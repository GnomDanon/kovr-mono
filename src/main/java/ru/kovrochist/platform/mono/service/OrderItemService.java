package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.order.OrderItemDto;
import ru.kovrochist.platform.mono.entity.OrderItems;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.order.OrderItemDoesNotExistsException;
import ru.kovrochist.platform.mono.repository.OrderItemRepository;
import ru.kovrochist.platform.mono.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

	private final OrderItemRepository orderItemRepository;

	private OrderItems getById(Long id) throws OrderItemDoesNotExistsException {
		return orderItemRepository.findById(id).orElseThrow(() -> new OrderItemDoesNotExistsException(id));
	}

	protected List<OrderItems> update(Orders order, OrderItemDto[] items) throws OrderItemDoesNotExistsException {
		List<OrderItems> result = new ArrayList<>();
		for (OrderItemDto item : items) {
			result.add(update(order, item));
		}
		return result;
	}

	private OrderItems update(Orders order, OrderItemDto updateInfo) throws OrderItemDoesNotExistsException {
		Long id = updateInfo.getId();
		if (id == null) {
			return orderItemRepository.save(new OrderItems().setProductType(updateInfo.getProductType()).setArea(updateInfo.getArea()).setComment(updateInfo.getComment()).setServices(updateInfo.getServices() == null ? null : String.join(StringUtil.SEPARATOR, updateInfo.getServices())).setContaminations(updateInfo.getContaminations() == null ? null : String.join(StringUtil.SEPARATOR, updateInfo.getContaminations())).setOrder(order));
		}
		OrderItems item = getById(id);
		return orderItemRepository.save(item.setProductType(updateInfo.getProductType()).setArea(updateInfo.getArea()).setComment(updateInfo.getComment()).setServices(String.join(StringUtil.SEPARATOR, updateInfo.getServices())).setContaminations(String.join(StringUtil.SEPARATOR, updateInfo.getContaminations())));
	}

	protected void updateServices(Long id, String[] services) throws OrderItemDoesNotExistsException {
		OrderItems item = getById(id);
		orderItemRepository.save(item.setServices(String.join(StringUtil.SEPARATOR, services)));
	}
}
