package ru.kovrochist.platform.mono.mapper.order;

import ru.kovrochist.platform.mono.dto.order.CreateOrderDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.status.CreateOrderStatusDto;
import ru.kovrochist.platform.mono.dto.order.status.OrderStatusDto;
import ru.kovrochist.platform.mono.entity.OrderStatus;
import ru.kovrochist.platform.mono.entity.Orders;

public class OrderMapper {

	public static Orders map(CreateOrderDto orderDto) {
		return new Orders()
				.setItemsCount(orderDto.getItemsCount())
				.setComment(orderDto.getComment())
				.setAdditionalService(orderDto.getAdditionalService());
	}

	public static OrderDto map(Orders order) {
		return new OrderDto()
				.setId(order.getId())
				.setItemsCount(order.getItemsCount())
				.setComment(order.getComment())
				.setAdditionalService(order.getAdditionalService())
				.setCost(order.getCost())
				.setCreatedAt(order.getCreatedAt())
				.setUpdatedAt(order.getUpdatedAt())
				.setClientId(order.getClient().getId())
				.setEmployeeId(order.getEmployee().getId())
				.setAddressId(order.getAddress().getId())
				.setOrderStatusId(order.getStatus().getId());
	}

	public static OrderStatus map(CreateOrderStatusDto orderStatusDto) {
		return new OrderStatus().setName(orderStatusDto.getName());
	}

	public static OrderStatusDto map(OrderStatus orderStatus) {
		return new OrderStatusDto().setId(orderStatus.getId()).setName(orderStatus.getName());
	}
}
