package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.MetadataApi;
import ru.kovrochist.platform.mono.dto.metadata.TypeWrapper;
import ru.kovrochist.platform.mono.exception.metadata.CityAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ClientStatusAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ContamintaionAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.DistrictAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ProductAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ServiceAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.SourceAlreadyExistsException;
import ru.kovrochist.platform.mono.service.MetadataService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MetadataController implements MetadataApi {

	private final MetadataService metadataService;

	@Override
	public ResponseEntity<List<String>> getOrderStatuses() {
		return ResponseEntity.ok(metadataService.getOrderStatuses());
	}

	@Override
	public ResponseEntity<List<String>> addService(TypeWrapper type) throws ServiceAlreadyExistsException {
		return ResponseEntity.ok(metadataService.createService(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getServices() {
		return ResponseEntity.ok(metadataService.getServices());
	}

	@Override
	public ResponseEntity<List<String>> removeService(String service) {
		return ResponseEntity.ok(metadataService.removeService(service));
	}

	@Override
	public ResponseEntity<List<String>> addCity(TypeWrapper type) throws CityAlreadyExistsException {
		return ResponseEntity.ok(metadataService.createCity(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getCities() {
		return ResponseEntity.ok(metadataService.getCities());
	}

	@Override
	public ResponseEntity<List<String>> removeCity(String city) {
		return ResponseEntity.ok(metadataService.removeCity(city));
	}

	@Override
	public ResponseEntity<List<String>> addProductType(TypeWrapper type) throws ProductAlreadyExistsException {
		return ResponseEntity.ok(metadataService.createProduct(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getProductTypes() {
		return ResponseEntity.ok(metadataService.getProducts());
	}

	@Override
	public ResponseEntity<List<String>> removeProductType(String type) {
		return ResponseEntity.ok(metadataService.removeProduct(type));
	}

	@Override
	public ResponseEntity<List<String>> addContaminationType(TypeWrapper type) throws ContamintaionAlreadyExistsException {
		return ResponseEntity.ok(metadataService.createContamination(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getContaminationTypes() {
		return ResponseEntity.ok(metadataService.getContaminations());
	}

	@Override
	public ResponseEntity<List<String>> removeContaminationType(String type) {
		return ResponseEntity.ok(metadataService.removeContamination(type));
	}

	@Override
	public ResponseEntity<List<String>> getDeliveryOptions() {
		return ResponseEntity.ok(metadataService.getDeliveryTypes());
	}

	@Override
	public ResponseEntity<List<String>> addRequestSource(TypeWrapper type) throws SourceAlreadyExistsException {
		return ResponseEntity.ok(metadataService.createSource(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getRequestSources() {
		return ResponseEntity.ok(metadataService.getSources());
	}

	@Override
	public ResponseEntity<List<String>> removeRequestSource(String source) {
		return ResponseEntity.ok(metadataService.removeSource(source));
	}

	@Override
	public ResponseEntity<List<String>> addClientStatus(TypeWrapper type) throws ClientStatusAlreadyExistsException {
		return ResponseEntity.ok(metadataService.createClientStatus(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getClientStatuses() {
		return ResponseEntity.ok(metadataService.getClientStatuses());
	}

	@Override
	public ResponseEntity<List<String>> removeClientStatus(String status) {
		return ResponseEntity.ok(metadataService.removeClientStatus(status));
	}

	@Override
	public ResponseEntity<List<String>> addDistrict(TypeWrapper type) throws DistrictAlreadyExistsException {
		return ResponseEntity.ok(metadataService.createDistrict(type.getType()));
	}

	@Override
	public ResponseEntity<List<String>> getDistricts() {
		return ResponseEntity.ok(metadataService.getDistricts());
	}

	@Override
	public ResponseEntity<List<String>> removeDistrict(String district) {
		return ResponseEntity.ok(metadataService.removeDistrict(district));
	}
}
