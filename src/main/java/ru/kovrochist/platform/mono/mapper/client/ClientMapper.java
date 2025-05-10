package ru.kovrochist.platform.mono.mapper.client;

import ru.kovrochist.platform.mono.dto.user.RoleWrapper;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.type.Role;

public class ClientMapper {

	public static UserDto map(Clients client) {
		return new UserDto()
				.setId(client.getId())
				.setPhone(client.getPhone())
				.setFirstName(client.getFirstName())
				.setLastName(client.getLastName())
				.setBirthday(client.getBirthday())
				.setCity(client.getCity())
				.setAddress(client.getAddress())
				.setGender(client.getGender() == null ? "" : client.getGender().getLabel())
				.setRole(new RoleWrapper().setValue(Role.CLIENT.getValue()));
	}
}
