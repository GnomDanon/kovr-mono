package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.MetadataApi;
import ru.kovrochist.platform.mono.dto.metadata.TypeWrapper;
import ru.kovrochist.platform.mono.exception.ResourceAccessException;
import ru.kovrochist.platform.mono.exception.metadata.CityAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ClientStatusAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ContamintaionAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.DistrictAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ProductAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ServiceAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.SourceAlreadyExistsException;
import ru.kovrochist.platform.mono.security.access.AccessFilter;
import ru.kovrochist.platform.mono.service.MetadataService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MetadataController implements MetadataApi {

	private final MetadataService metadataService;

	private final AccessFilter accessFilter;

	private void access() throws ResourceAccessException {
		accessFilter.operatorOrAdmin();
	}

	@Override
	public ResponseEntity<List<String>> getOrderStatuses() throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.getOrderStatuses());
	}

	@Override
	public ResponseEntity<List<String>> addService(TypeWrapper type) throws ServiceAlreadyExistsException, ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.createService(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getServices() throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.getServices());
	}

	@Override
	public ResponseEntity<List<String>> removeService(String service) throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.removeService(service));
	}

	@Override
	public ResponseEntity<List<String>> addCity(TypeWrapper type) throws CityAlreadyExistsException, ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.createCity(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getCities() throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.getCities());
	}

	@Override
	public ResponseEntity<List<String>> removeCity(String city) throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.removeCity(city));
	}

	@Override
	public ResponseEntity<List<String>> addProductType(TypeWrapper type) throws ProductAlreadyExistsException, ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.createProduct(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getProductTypes() throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.getProducts());
	}

	@Override
	public ResponseEntity<List<String>> removeProductType(String type) throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.removeProduct(type));
	}

	@Override
	public ResponseEntity<List<String>> addContaminationType(TypeWrapper type) throws ContamintaionAlreadyExistsException, ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.createContamination(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getContaminationTypes() throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.getContaminations());
	}

	@Override
	public ResponseEntity<List<String>> removeContaminationType(String type) throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.removeContamination(type));
	}

	@Override
	public ResponseEntity<List<String>> getDeliveryOptions() throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.getDeliveryTypes());
	}

	@Override
	public ResponseEntity<List<String>> addRequestSource(TypeWrapper type) throws SourceAlreadyExistsException, ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.createSource(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getRequestSources() throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.getSources());
	}

	@Override
	public ResponseEntity<List<String>> removeRequestSource(String source) throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.removeSource(source));
	}

	@Override
	public ResponseEntity<List<String>> addClientStatus(TypeWrapper type) throws ClientStatusAlreadyExistsException, ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.createClientStatus(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getClientStatuses() throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.getClientStatuses());
	}

	@Override
	public ResponseEntity<List<String>> removeClientStatus(String status) throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.removeService(status));
	}

	@Override
	public ResponseEntity<List<String>> addDistrict(TypeWrapper type) throws DistrictAlreadyExistsException, ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.createDistrict(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getDistricts() throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.getDistricts());
	}

	@Override
	public ResponseEntity<List<String>> removeDistrict(String district) throws ResourceAccessException {
		access();
		return ResponseEntity.ok(metadataService.removeDistrict(district));
	}
}
