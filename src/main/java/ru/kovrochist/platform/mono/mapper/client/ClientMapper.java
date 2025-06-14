package ru.kovrochist.platform.mono.mapper.client;

import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.type.Role;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClientMapper {

	public static UserDto mapToUser(Clients client) {
		return new UserDto()
				.setId(client.getId())
				.setPhone(client.getPhone())
				.setFirstName(client.getFirstName())
				.setLastName(client.getLastName())
				.setBirthday(client.getBirthday())
				.setCity(client.getCity())
				.setAddress(client.getAddress())
				.setGender(client.getGender() == null ? "" : client.getGender().getLabel())
				.setRole(Role.CLIENT.getLabel())
				.setCreatedAt(client.getCreatedAt());
	}

	public static ClientDto map(Clients client) {
		ClientDto clientDto = new ClientDto()
				.setOrdersCount(client.getOrders() == null ? 0 : client.getOrders().size())
				.setDaysWithoutOrders(getDaysWithoutOrders(client.getOrders()))
				.setStatus(client.getStatus())
				.setAverageCheck(getAverageCheck(client.getOrders()));

		clientDto.setCity(client.getCity())
				.setAddress(client.getAddress())
				.setId(client.getId())
				.setPhone(client.getPhone())
				.setRole(client.getRole().getLabel())
				.setFirstName(client.getFirstName())
				.setLastName(client.getLastName())
				.setBirthday(client.getBirthday())
				.setGender(client.getGender() == null ? "" : client.getGender().getLabel())
				.setCreatedAt(client.getCreatedAt());

		return clientDto;
	}

	private static Long getDaysWithoutOrders(List<Orders> orders) {
		if (orders == null)
			return 0L;
		Date today = new Date();
		return orders.stream()
				.map(order -> TimeUnit.MILLISECONDS.toDays(Math.abs(order.getUpdatedAt().getTime() - today.getTime())))
				.max(Long::compare)
				.orElse(0L);
	}

	private static Double getAverageCheck(List<Orders> orders) {
		if (orders == null)
			return 0D;
		double result = 0D;
		int ordersCount = 0;
		for (Orders order : orders) {
			if (order.getPrice() != null) {
				result += order.getPrice();
				ordersCount++;
			}
		}
		return result / ordersCount;
	}
}
