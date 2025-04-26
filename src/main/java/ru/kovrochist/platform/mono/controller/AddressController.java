package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.AddressApi;
import ru.kovrochist.platform.mono.dto.address.AddressDto;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.service.AddressService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AddressController implements AddressApi {

	private final AddressService addressService;

	@Override
	public ResponseEntity<AddressDto> get(UUID id) throws DoesNotExistException {
		return ResponseEntity.ok(addressService.getById(id));
	}
}
