package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.entity.type.Cities;
import ru.kovrochist.platform.mono.entity.type.ClientStatuses;
import ru.kovrochist.platform.mono.entity.type.Contaminations;
import ru.kovrochist.platform.mono.entity.type.Districts;
import ru.kovrochist.platform.mono.entity.Metadata;
import ru.kovrochist.platform.mono.entity.type.Products;
import ru.kovrochist.platform.mono.entity.type.Services;
import ru.kovrochist.platform.mono.entity.type.Sources;
import ru.kovrochist.platform.mono.exception.ResourceConflictException;
import ru.kovrochist.platform.mono.exception.metadata.CityAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ClientStatusAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ContamintaionAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.DistrictAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ProductAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ServiceAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.SourceAlreadyExistsException;
import ru.kovrochist.platform.mono.repository.MetadataRepository;
import ru.kovrochist.platform.mono.repository.metadata.CityRepository;
import ru.kovrochist.platform.mono.repository.metadata.ClientStatusRepository;
import ru.kovrochist.platform.mono.repository.metadata.ContaminationRepository;
import ru.kovrochist.platform.mono.repository.metadata.DistrictRepository;
import ru.kovrochist.platform.mono.repository.metadata.ProductRepository;
import ru.kovrochist.platform.mono.repository.metadata.ServiceRepository;
import ru.kovrochist.platform.mono.repository.metadata.SourceRepository;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MetadataService {

	private final CityRepository cityRepository;
	private final ClientStatusRepository clientStatusRepository;
	private final ContaminationRepository contaminationRepository;
	private final DistrictRepository districtRepository;
	private final ProductRepository productRepository;
	private final ServiceRepository serviceRepository;
	private final SourceRepository sourceRepository;

	public List<String> getDeliveryTypes() {
		List<String> result = new ArrayList<>();
		DeliveryType[] types = DeliveryType.values();

		for (DeliveryType type : types)
			result.add(type.getLabel());

		return result;
	}

	public List<String> getOrderStatuses() {
		List<String> result = new ArrayList<>();
		OrderStatus[] statuses = OrderStatus.values();

		for (OrderStatus status : statuses)
			result.add(status.getLabel());

		return result;
	}

	public List<String> createCity(String name) throws CityAlreadyExistsException {
		create(name, cityRepository, CityAlreadyExistsException::new, Cities::new);
		return getCities();
	}

	public List<String> getCities() {
		return get(cityRepository);
	}

	public List<String> removeCity(String name) {
		remove(name, cityRepository);
		return getCities();
	}

	public List<String> createClientStatus(String name) throws ClientStatusAlreadyExistsException {
		create(name, clientStatusRepository, ClientStatusAlreadyExistsException::new, ClientStatuses::new);
		return getClientStatuses();
	}

	public List<String> getClientStatuses() {
		return get(clientStatusRepository);
	}

	public List<String> createContamination(String name) throws ContamintaionAlreadyExistsException {
		create(name, contaminationRepository, ContamintaionAlreadyExistsException::new, Contaminations::new);
		return getContaminations();
	}

	public List<String> getContaminations() {
		return get(contaminationRepository);
	}

	public List<String> removeContamination(String name) {
		remove(name, contaminationRepository);
		return getContaminations();
	}

	public List<String> createDistrict(String name) throws DistrictAlreadyExistsException {
		create(name, districtRepository, DistrictAlreadyExistsException::new, Districts::new);
		return getDistricts();
	}

	public List<String> getDistricts() {
		return get(districtRepository);
	}

	public List<String> removeDistrict(String name) {
		remove(name, districtRepository);
		return getDistricts();
	}

	public List<String> createProduct(String name) throws ProductAlreadyExistsException {
		create(name, productRepository, ProductAlreadyExistsException::new, Products::new);
		return getProducts();
	}

	public List<String> getProducts() {
		return get(productRepository);
	}

	public List<String> removeProduct(String name) {
		remove(name, productRepository);
		return getProducts();
	}

	public List<String> createService(String name) throws ServiceAlreadyExistsException {
		create(name, serviceRepository, ServiceAlreadyExistsException::new, Services::new);
		return getServices();
	}

	public List<String> getServices() {
		return get(serviceRepository);
	}

	public List<String> removeService(String name) {
		remove(name, serviceRepository);
		return getServices();
	}

	public List<String> createSource(String name) throws SourceAlreadyExistsException {
		create(name, sourceRepository, SourceAlreadyExistsException::new, Sources::new);
		return getSources();
	}

	public List<String> getSources() {
		return get(sourceRepository);
	}

	public List<String> removeSource(String name) {
		remove(name, sourceRepository);
		return getSources();
	}

	private <T, ID, E extends ResourceConflictException, R extends MetadataRepository<T> & JpaRepository<T, ID>> void create(String name, R repository, Function<String, E> exceptionSupplier, Function<String, T> metadataSupplier) throws E {
		T metadata = repository.findByName(name).orElse(null);

		if (metadata != null)
			throw exceptionSupplier.apply(name);

		repository.save(metadataSupplier.apply(name));
	}

	private <T extends Metadata, ID, R extends MetadataRepository<T> & JpaRepository<T, ID>> List<String> get(R repository) {
		List<String> result = new ArrayList<>();
		Iterable<T> meta = repository.findAll();

		for (T metadata : meta) {
			result.add(metadata.getName());
		}

		return result;
	}

	private <T, ID, R extends MetadataRepository<T> & JpaRepository<T, ID>> void remove(String name, R repository) {
		T metadata = repository.findByName(name).orElse(null);

		if (metadata == null)
			return;

		repository.delete(metadata);
	}
}
