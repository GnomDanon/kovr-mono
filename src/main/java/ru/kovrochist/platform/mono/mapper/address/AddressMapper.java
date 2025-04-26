package ru.kovrochist.platform.mono.mapper.address;

import ru.kovrochist.platform.mono.dto.address.AddressDto;
import ru.kovrochist.platform.mono.dto.address.CreateAddressDto;
import ru.kovrochist.platform.mono.entity.Address;

public class AddressMapper {

	public static Address map(CreateAddressDto addressDto) {
		return new Address()
				.setRegion(addressDto.getRegion())
				.setCity(addressDto.getCity())
				.setStreet(addressDto.getStreet())
				.setHouseNumber(addressDto.getHouseNumber())
				.setFlatNumber(addressDto.getFlatNumber())
				.setFloorNumber(addressDto.getFloorNumber());
	}

	public static Address map(AddressDto addressDto) {
		return new Address()
				.setId(addressDto.getId())
				.setRegion(addressDto.getRegion())
				.setCity(addressDto.getCity())
				.setStreet(addressDto.getStreet())
				.setHouseNumber(addressDto.getHouseNumber())
				.setFlatNumber(addressDto.getFlatNumber())
				.setFloorNumber(addressDto.getFloorNumber());
	}

	public static AddressDto map(Address address) {
		return new AddressDto()
				.setId(address.getId())
				.setRegion(address.getRegion())
				.setCity(address.getCity())
				.setStreet(address.getStreet())
				.setHouseNumber(address.getHouseNumber())
				.setFlatNumber(address.getFlatNumber())
				.setFloorNumber(address.getFloorNumber());
	}
}
