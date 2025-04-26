package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.address.AddressDto;
import ru.kovrochist.platform.mono.dto.address.CreateAddressDto;
import ru.kovrochist.platform.mono.entity.Address;
import ru.kovrochist.platform.mono.exception.address.AddressDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.address.AddressMapper;
import ru.kovrochist.platform.mono.repository.AddressRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {

	private final AddressRepository addressRepository;

	public AddressDto create(CreateAddressDto createAddressDto) {
		Address address = addressRepository.find(
				createAddressDto.getRegion(),
				createAddressDto.getCity(),
				createAddressDto.getStreet(),
				createAddressDto.getHouseNumber(),
				createAddressDto.getFlatNumber(),
				createAddressDto.getFloorNumber()
		).orElse(null);

		if (address != null)
			return AddressMapper.map(address);

		address = addressRepository.save(AddressMapper.map(createAddressDto));
		return AddressMapper.map(address);
	}

	public AddressDto getById(UUID id) throws AddressDoesNotExistException {
		Address address = addressRepository.findById(id).orElseThrow(() -> new AddressDoesNotExistException(id));
		return AddressMapper.map(address);
	}
}
