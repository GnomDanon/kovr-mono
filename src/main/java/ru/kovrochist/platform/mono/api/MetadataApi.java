package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.metadata.TypeWrapper;
import ru.kovrochist.platform.mono.exception.ResourceAccessException;
import ru.kovrochist.platform.mono.exception.metadata.CityAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ClientStatusAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ContamintaionAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.DistrictAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ProductAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.ServiceAlreadyExistsException;
import ru.kovrochist.platform.mono.exception.metadata.SourceAlreadyExistsException;

import java.util.List;

@RequestMapping("/metadata")
@Tag(name = "Метадата")
public interface MetadataApi {

	@Operation(summary = "Получение статусов заказа")
	@GetMapping("/statuses")
	ResponseEntity<List<String>> getOrderStatuses() throws ResourceAccessException;


	@Operation(summary = "Загрузка услуги")
	@PostMapping("/services")
	ResponseEntity<List<String>> addService(@RequestBody TypeWrapper type) throws ServiceAlreadyExistsException, ResourceAccessException;

	@Operation(summary = "Получение услуг")
	@GetMapping("/services")
	ResponseEntity<List<String>> getServices() throws ResourceAccessException;

	@Operation(summary = "Удаление услуги")
	@DeleteMapping("/services")
	ResponseEntity<List<String>> removeService(@RequestParam String service) throws ResourceAccessException;


	@Operation(summary = "Загрузка города")
	@PostMapping("/cities")
	ResponseEntity<List<String>> addCity(@RequestBody TypeWrapper type) throws CityAlreadyExistsException, ResourceAccessException;

	@Operation(summary = "Получение городов")
	@GetMapping("/cities")
	ResponseEntity<List<String>> getCities() throws ResourceAccessException;

	@Operation(summary = "Удаление города")
	@DeleteMapping("/cities")
	ResponseEntity<List<String>> removeCity(@RequestParam String city) throws ResourceAccessException;


	@Operation(summary = "Загрузка типа изделия")
	@PostMapping("/product-types")
	ResponseEntity<List<String>> addProductType(@RequestBody TypeWrapper type) throws ProductAlreadyExistsException, ResourceAccessException;

	@Operation(summary = "Получение типов изделий")
	@GetMapping("/product-types")
	ResponseEntity<List<String>> getProductTypes() throws ResourceAccessException;

	@Operation(summary = "Удаление типа изделия")
	@DeleteMapping("/product-types")
	ResponseEntity<List<String>> removeProductType(@RequestParam String type) throws ResourceAccessException;


	@Operation(summary = "Загрузка типа загрязнение")
	@PostMapping("/contamination-types")
	ResponseEntity<List<String>> addContaminationType(@RequestBody TypeWrapper type) throws ContamintaionAlreadyExistsException, ResourceAccessException;

	@Operation(summary = "Получение типов загрязнения")
	@GetMapping("/contamination-types")
	ResponseEntity<List<String>> getContaminationTypes() throws ResourceAccessException;

	@Operation(summary = "Удаление типа загрязнения")
	@DeleteMapping("/contamination-types")
	ResponseEntity<List<String>> removeContaminationType(@RequestParam String type) throws ResourceAccessException;


	@Operation(summary = "Получение типов доставки")
	@GetMapping("/delivery-options")
	ResponseEntity<List<String>> getDeliveryOptions() throws ResourceAccessException;


	@Operation(summary = "Загрузка вида источника")
	@PostMapping("/request-sources")
	ResponseEntity<List<String>> addRequestSource(@RequestBody TypeWrapper type) throws SourceAlreadyExistsException, ResourceAccessException;

	@Operation(summary = "Получение видов источников")
	@GetMapping("/request-sources")
	ResponseEntity<List<String>> getRequestSources() throws ResourceAccessException;

	@Operation(summary = "Удаление вида источника")
	@DeleteMapping("/request-sources")
	ResponseEntity<List<String>> removeRequestSource(@RequestParam String source) throws ResourceAccessException;


	@Operation(summary = "Загрузка статуса клиента")
	@PostMapping("/client-statuses")
	ResponseEntity<List<String>> addClientStatus(@RequestBody TypeWrapper type) throws ClientStatusAlreadyExistsException, ResourceAccessException;

	@Operation(summary = "Получение статусов клиента")
	@GetMapping("/client-statuses")
	ResponseEntity<List<String>> getClientStatuses() throws ResourceAccessException;

	@Operation(summary = "Удаление статуса клиента")
	@DeleteMapping("/client-statuses")
	ResponseEntity<List<String>> removeClientStatus(@RequestParam String status) throws ResourceAccessException;


	@Operation(summary = "Загрузка района")
	@PostMapping("/districts")
	ResponseEntity<List<String>> addDistrict(@RequestBody TypeWrapper type) throws DistrictAlreadyExistsException, ResourceAccessException;

	@Operation(summary = "Получение районов")
	@GetMapping("/districts")
	ResponseEntity<List<String>> getDistricts() throws ResourceAccessException;

	@Operation(summary = "Удаление района")
	@DeleteMapping("/districts")
	ResponseEntity<List<String>> removeDistrict(@RequestParam String district) throws ResourceAccessException;
}
