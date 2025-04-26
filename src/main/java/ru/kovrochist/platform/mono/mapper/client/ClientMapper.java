package ru.kovrochist.platform.mono.mapper.client;

import ru.kovrochist.platform.mono.dto.client.ClientDto;
import ru.kovrochist.platform.mono.dto.client.CreateClientDto;
import ru.kovrochist.platform.mono.dto.client.status.ClientStatusDto;
import ru.kovrochist.platform.mono.dto.client.status.CreateClientStatusDto;
import ru.kovrochist.platform.mono.entity.Client;
import ru.kovrochist.platform.mono.entity.ClientStatus;

public class ClientMapper {

	public static Client map(CreateClientDto clientDto) {
		return new Client()
				.setName(clientDto.getName())
				.setPhoneNumber(clientDto.getPhoneNumber())
				.setComment(clientDto.getComment());
	}

	public static ClientDto map(Client client) {
		return new ClientDto()
				.setId(client.getId())
				.setName(client.getName())
				.setPhoneNumber(client.getPhoneNumber())
				.setComment(client.getComment())
				.setAddressId(client.getAddress().getId())
				.setClientStatusId(client.getClientStatus().getId());
	}

	public static ClientStatus map(CreateClientStatusDto clientStatusDto) {
		return new ClientStatus().setName(clientStatusDto.getName());
	}

	public static ClientStatus map(ClientStatusDto clientStatusDto) {
		return new ClientStatus().setId(clientStatusDto.getId()).setName(clientStatusDto.getName());
	}

	public static ClientStatusDto map(ClientStatus clientStatus) {
		return new ClientStatusDto().setId(clientStatus.getId()).setName(clientStatus.getName());
	}
}
