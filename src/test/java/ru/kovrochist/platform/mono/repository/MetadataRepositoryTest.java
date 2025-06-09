package ru.kovrochist.platform.mono.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ru.kovrochist.platform.mono.entity.type.Cities;
import ru.kovrochist.platform.mono.entity.type.ClientStatuses;
import ru.kovrochist.platform.mono.entity.type.Contaminations;
import ru.kovrochist.platform.mono.entity.type.Districts;
import ru.kovrochist.platform.mono.entity.type.Products;
import ru.kovrochist.platform.mono.entity.type.Services;
import ru.kovrochist.platform.mono.entity.type.Sources;
import ru.kovrochist.platform.mono.repository.metadata.CityRepository;
import ru.kovrochist.platform.mono.repository.metadata.ClientStatusRepository;
import ru.kovrochist.platform.mono.repository.metadata.ContaminationRepository;
import ru.kovrochist.platform.mono.repository.metadata.DistrictRepository;
import ru.kovrochist.platform.mono.repository.metadata.ProductRepository;
import ru.kovrochist.platform.mono.repository.metadata.ServiceRepository;
import ru.kovrochist.platform.mono.repository.metadata.SourceRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=update")
public class MetadataRepositoryTest {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ClientStatusRepository clientStatusRepository;

	@Autowired
	private ContaminationRepository contaminationRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private SourceRepository sourceRepository;

	private String city;
	private String clientStatus;
	private String contamination;
	private String district;
	private String product;
	private String service;
	private String source;

	@BeforeEach
	void setup() {
		city = cityRepository.save(new Cities().setName("Киров")).getName();
		cityRepository.save(new Cities().setName("Кирово-Чепецк"));

		clientStatus = clientStatusRepository.save(new ClientStatuses().setName("Новый")).getName();
		clientStatusRepository.save(new ClientStatuses().setName("Постоянный"));

		contamination = contaminationRepository.save(new Contaminations().setName("Пластилин")).getName();
		contaminationRepository.save(new Contaminations().setName("Вино/варенье"));

		district = districtRepository.save(new Districts().setName("Центр")).getName();
		districtRepository.save(new Districts().setName("Юго-Запад"));

		product = productRepository.save(new Products().setName("Шерстяной")).getName();
		productRepository.save(new Products().setName("Синтетический"));

		service = serviceRepository.save(new Services().setName("Химчистка")).getName();
		serviceRepository.save(new Services().setName("Оверлок"));

		source = sourceRepository.save(new Sources().setName("Авито")).getName();
		sourceRepository.save(new Sources().setName("Рекомендации знакомых"));
	}

	@Test
	@DisplayName("Поиск справочников")
	void shouldFind() {
		Optional<Cities> foundCity = cityRepository.findByName(city);
		Optional<ClientStatuses> foundClientStatus = clientStatusRepository.findByName(clientStatus);
		Optional<Contaminations> foundContamination = contaminationRepository.findByName(contamination);
		Optional<Districts> foundDistrict = districtRepository.findByName(district);
		Optional<Products> foundProduct = productRepository.findByName(product);
		Optional<Services> foundService = serviceRepository.findByName(service);
		Optional<Sources> foundSource = sourceRepository.findByName(source);

		assertThat(foundCity).isPresent();
		assertThat(foundClientStatus).isPresent();
		assertThat(foundContamination).isPresent();
		assertThat(foundDistrict).isPresent();
		assertThat(foundProduct).isPresent();
		assertThat(foundService).isPresent();
		assertThat(foundSource).isPresent();

		assertThat(foundCity.get().getName()).isEqualTo(city);
		assertThat(foundClientStatus.get().getName()).isEqualTo(clientStatus);
		assertThat(foundContamination.get().getName()).isEqualTo(contamination);
		assertThat(foundDistrict.get().getName()).isEqualTo(district);
		assertThat(foundProduct.get().getName()).isEqualTo(product);
		assertThat(foundService.get().getName()).isEqualTo(service);
		assertThat(foundSource.get().getName()).isEqualTo(source);
	}
}
