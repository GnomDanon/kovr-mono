package ru.kovrochist.platform.mono.mapper.order;

import ru.kovrochist.platform.mono.dto.employee.AssignedEmployeeDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.OrderItemDto;
import ru.kovrochist.platform.mono.entity.AssignedEmployees;
import ru.kovrochist.platform.mono.entity.OrderItems;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.mapper.client.ClientMapper;
import ru.kovrochist.platform.mono.mapper.employee.EmployeeMapper;
import ru.kovrochist.platform.mono.type.DeliveryDay;
import ru.kovrochist.platform.mono.util.StringUtil;

import java.util.Arrays;
import java.util.List;

public class OrderMapper {

	public static OrderDto map(Orders order) {
		return new OrderDto()
				.setId(order.getId())
				.setOrderNumber(order.getId().toString())
				.setClient(ClientMapper.map(order.getClient()))
				.setStatus(order.getStatus().getLabel())
				.setItems(mapItems(order.getItems()))
				.setComment(order.getComment())
				.setDeliveryType(order.getDeliveryType() == null ? null : order.getDeliveryType().getLabel())
				.setCreatedAt(order.getCreatedAt())
				.setPhone(order.getPhone())
				.setCity(order.getCity())
				.setAddress(order.getAddress())
				.setDistrict(order.getDistrict())
				.setDeliveryDays(order.getDeliveryDays() == null ? null : getDeliveryDays(order.getDeliveryDays()))
				.setDeliveryTimeStart(order.getDeliveryTimeStart())
				.setDeliveryTimeEnd(order.getDeliveryTimeEnd())
				.setDiscount(order.getDiscount())
				.setSources(order.getSources() == null ? null : order.getSources().split(StringUtil.SEPARATOR))
				.setAssignees(mapEmployees(order.getEmployees()));
	}

	public static OrderItemDto[] mapItems(List<OrderItems> items) {
		if (items == null)
			return null;
		return items.stream()
				.map(OrderMapper::mapItem)
				.toArray(OrderItemDto[]::new);
	}

	public static OrderItemDto mapItem(OrderItems item) {
		return new OrderItemDto()
				.setId(item.getId())
				.setProductType(item.getProductType())
				.setArea(item.getArea())
				.setComment(item.getComment())
				.setServices(item.getServices() == null ? null : item.getServices().split(StringUtil.SEPARATOR))
				.setContaminations(item.getContaminations() == null ? null : item.getContaminations().split(StringUtil.SEPARATOR));
	}

	public static AssignedEmployeeDto[] mapEmployees(List<AssignedEmployees> employees) {
		if (employees == null)
			return null;
		return employees.stream()
				.map(EmployeeMapper::mapAssigned)
				.toArray(AssignedEmployeeDto[]::new);
	}

	public static String[] getDeliveryDays(String deliveryDays) {
		String[] labels = deliveryDays.split(StringUtil.SEPARATOR);
		return Arrays.stream(labels).map(label -> DeliveryDay.valueOf(label).getLabel()).toArray(String[]::new);
	}
}
