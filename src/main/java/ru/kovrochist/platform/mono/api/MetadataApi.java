package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kovrochist.platform.mono.dto.metadata.TypeWrapper;
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
	ResponseEntity<List<String>> getOrderStatuses();


	@Operation(summary = "Загрузка услуги")
	@PostMapping("/services")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> addService(@RequestBody TypeWrapper type) throws ServiceAlreadyExistsException;

	@Operation(summary = "Получение услуг")
	@GetMapping("/services")
	ResponseEntity<List<String>> getServices();

	@Operation(summary = "Удаление услуги")
	@DeleteMapping("/services")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> removeService(@RequestParam String service);


	@Operation(summary = "Загрузка города")
	@PostMapping("/cities")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> addCity(@RequestBody TypeWrapper type) throws CityAlreadyExistsException;

	@Operation(summary = "Получение городов")
	@GetMapping("/cities")
	ResponseEntity<List<String>> getCities();

	@Operation(summary = "Удаление города")
	@DeleteMapping("/cities")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> removeCity(@RequestParam String city);


	@Operation(summary = "Загрузка типа изделия")
	@PostMapping("/product-types")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> addProductType(@RequestBody TypeWrapper type) throws ProductAlreadyExistsException;

	@Operation(summary = "Получение типов изделий")
	@GetMapping("/product-types")
	ResponseEntity<List<String>> getProductTypes();

	@Operation(summary = "Удаление типа изделия")
	@DeleteMapping("/product-types")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> removeProductType(@RequestParam String type);


	@Operation(summary = "Загрузка типа загрязнение")
	@PostMapping("/contamination-types")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> addContaminationType(@RequestBody TypeWrapper type) throws ContamintaionAlreadyExistsException;

	@Operation(summary = "Получение типов загрязнения")
	@GetMapping("/contamination-types")
	ResponseEntity<List<String>> getContaminationTypes();

	@Operation(summary = "Удаление типа загрязнения")
	@DeleteMapping("/contamination-types")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> removeContaminationType(@RequestParam String type);


	@Operation(summary = "Получение типов доставки")
	@GetMapping("/delivery-options")
	ResponseEntity<List<String>> getDeliveryOptions();


	@Operation(summary = "Загрузка вида источника")
	@PostMapping("/request-sources")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> addRequestSource(@RequestBody TypeWrapper type) throws SourceAlreadyExistsException;

	@Operation(summary = "Получение видов источников")
	@GetMapping("/request-sources")
	ResponseEntity<List<String>> getRequestSources();

	@Operation(summary = "Удаление вида источника")
	@DeleteMapping("/request-sources")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> removeRequestSource(@RequestParam String source);


	@Operation(summary = "Загрузка статуса клиента")
	@PostMapping("/client-statuses")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> addClientStatus(@RequestBody TypeWrapper type) throws ClientStatusAlreadyExistsException;

	@Operation(summary = "Получение статусов клиента")
	@GetMapping("/client-statuses")
	ResponseEntity<List<String>> getClientStatuses();

	@Operation(summary = "Удаление статуса клиента")
	@DeleteMapping("/client-statuses")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> removeClientStatus(@RequestParam String status);


	@Operation(summary = "Загрузка района")
	@PostMapping("/districts")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> addDistrict(@RequestBody TypeWrapper type) throws DistrictAlreadyExistsException;

	@Operation(summary = "Получение районов")
	@GetMapping("/districts")
	ResponseEntity<List<String>> getDistricts();

	@Operation(summary = "Удаление района")
	@DeleteMapping("/districts")
	@PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
	ResponseEntity<List<String>> removeDistrict(@RequestParam String district);
}
