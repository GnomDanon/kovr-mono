package ru.kovrochist.platform.mono.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kovrochist.platform.mono.entity.type.Cities;
import ru.kovrochist.platform.mono.entity.type.ClientStatuses;
import ru.kovrochist.platform.mono.entity.type.Contaminations;
import ru.kovrochist.platform.mono.entity.type.Districts;
import ru.kovrochist.platform.mono.entity.type.Products;
import ru.kovrochist.platform.mono.entity.type.Services;
import ru.kovrochist.platform.mono.entity.type.Sources;
import ru.kovrochist.platform.mono.exception.ResourceConflictException;
import ru.kovrochist.platform.mono.repository.metadata.CityRepository;
import ru.kovrochist.platform.mono.repository.metadata.ClientStatusRepository;
import ru.kovrochist.platform.mono.repository.metadata.ContaminationRepository;
import ru.kovrochist.platform.mono.repository.metadata.DistrictRepository;
import ru.kovrochist.platform.mono.repository.metadata.ProductRepository;
import ru.kovrochist.platform.mono.repository.metadata.ServiceRepository;
import ru.kovrochist.platform.mono.repository.metadata.SourceRepository;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetadataServiceTest {

	@Mock private CityRepository cityRepository;
	@Mock private ClientStatusRepository clientStatusRepository;
	@Mock private ContaminationRepository contaminationRepository;
	@Mock private DistrictRepository districtRepository;
	@Mock private ProductRepository productRepository;
	@Mock private ServiceRepository serviceRepository;
	@Mock private SourceRepository sourceRepository;

	@InjectMocks
	private MetadataService metadataService;

	private final String testName = "TestName";
	private final String existingName = "ExistingName";

	@BeforeEach
	void setUp() {
		lenient().when(cityRepository.findAll()).thenReturn(Collections.singletonList(new Cities(existingName)));
		lenient().when(clientStatusRepository.findAll()).thenReturn(Collections.singletonList(new ClientStatuses(existingName)));
		lenient().when(contaminationRepository.findAll()).thenReturn(Collections.singletonList(new Contaminations(existingName)));
		lenient().when(districtRepository.findAll()).thenReturn(Collections.singletonList(new Districts(existingName)));
		lenient().when(productRepository.findAll()).thenReturn(Collections.singletonList(new Products(existingName)));
		lenient().when(serviceRepository.findAll()).thenReturn(Collections.singletonList(new Services(existingName)));
		lenient().when(sourceRepository.findAll()).thenReturn(Collections.singletonList(new Sources(existingName)));
	}

	@Test
	@DisplayName("Получение типов доставки")
	void getDeliveryTypes_ShouldReturnAllDeliveryTypeLabels() {
		List<String> result = metadataService.getDeliveryTypes();

		assertNotNull(result);
		assertEquals(DeliveryType.values().length, result.size());
	}

	@Test
	@DisplayName("Получение статусов заказа")
	void getOrderStatuses_ShouldReturnAllOrderStatusLabels() {
		List<String> result = metadataService.getOrderStatuses();

		assertNotNull(result);
		assertEquals(OrderStatus.values().length, result.size());
	}

	@Test
	@DisplayName("Создание города")
	void createCity_WithNewName_ShouldReturnUpdatedList() throws Exception {
		when(cityRepository.findByName(testName)).thenReturn(Optional.empty());

		List<String> result = metadataService.createCity(testName);

		assertNotNull(result);
		assertTrue(result.contains(existingName));
		verify(cityRepository).save(any(Cities.class));
	}

	@Test
	@DisplayName("Создание города с существующим названием")
	void createCity_WithExistingName_ShouldThrowException() {
		when(cityRepository.findByName(existingName)).thenReturn(Optional.of(new Cities(existingName)));

		assertThrows(ResourceConflictException.class, () -> metadataService.createCity(existingName));
	}

	@Test
	@DisplayName("Получение городов")
	void getCities_ShouldReturnAllCities() {
		List<String> result = metadataService.getCities();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(existingName, result.get(0));
	}

	@Test
	@DisplayName("Удаление города")
	void removeCity_WithExistingName_ShouldRemoveAndReturnUpdatedList() {
		when(cityRepository.findByName(existingName)).thenReturn(Optional.of(new Cities(existingName)));

		List<String> result = metadataService.removeCity(existingName);

		assertNotNull(result);
		verify(cityRepository).delete(any(Cities.class));
	}

	@Test
	@DisplayName("Удаление города с несуществующим городом")
	void removeCity_WithNonExistingName_ShouldReturnSameList() {
		String nonExistingName = "NonExistingName";
		when(cityRepository.findByName(nonExistingName)).thenReturn(Optional.empty());

		List<String> result = metadataService.removeCity(nonExistingName);

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(cityRepository, never()).delete(any());
	}

	@Test
	@DisplayName("Создание статуса клиента")
	void createClientStatus_ShouldWorkLikeCities() throws Exception {
		when(clientStatusRepository.findByName(testName)).thenReturn(Optional.empty());

		List<String> result = metadataService.createClientStatus(testName);

		assertNotNull(result);
		verify(clientStatusRepository).save(any(ClientStatuses.class));
	}

	@Test
	@DisplayName("Получение статусов клиента")
	void getClientStatuses_ShouldWorkLikeCities() {
		List<String> result = metadataService.getClientStatuses();

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	@DisplayName("Удаление статуса клиента")
	void removeClientStatus_ShouldWorkLikeCities() {
		when(clientStatusRepository.findByName(existingName)).thenReturn(Optional.of(new ClientStatuses(existingName)));

		metadataService.removeClientStatus(existingName);
		verify(clientStatusRepository).delete(any(ClientStatuses.class));
	}

	@Test
	@DisplayName("Создание типа загрязнения")
	void createContamination_ShouldWorkLikeCities() throws Exception {
		when(contaminationRepository.findByName(testName)).thenReturn(Optional.empty());

		metadataService.createContamination(testName);
		verify(contaminationRepository).save(any(Contaminations.class));
	}

	@Test
	@DisplayName("Создание района")
	void createDistrict_ShouldWorkLikeCities() throws Exception {
		when(districtRepository.findByName(testName)).thenReturn(Optional.empty());

		metadataService.createDistrict(testName);
		verify(districtRepository).save(any(Districts.class));
	}

	@Test
	@DisplayName("Создание вида изделия")
	void createProduct_ShouldWorkLikeCities() throws Exception {
		when(productRepository.findByName(testName)).thenReturn(Optional.empty());

		metadataService.createProduct(testName);
		verify(productRepository).save(any(Products.class));
	}

	@Test
	@DisplayName("Создание вида услуги")
	void createService_ShouldWorkLikeCities() throws Exception {
		when(serviceRepository.findByName(testName)).thenReturn(Optional.empty());

		metadataService.createService(testName);
		verify(serviceRepository).save(any(Services.class));
	}

	@Test
	@DisplayName("Создание источника")
	void createSource_ShouldWorkLikeCities() throws Exception {
		when(sourceRepository.findByName(testName)).thenReturn(Optional.empty());

		metadataService.createSource(testName);
		verify(sourceRepository).save(any(Sources.class));
	}
}
