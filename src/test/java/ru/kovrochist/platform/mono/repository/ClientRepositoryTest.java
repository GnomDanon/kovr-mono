package ru.kovrochist.platform.mono.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ru.kovrochist.platform.mono.entity.Clients;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=update")
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository repository;

	private Clients createClient(String phone, String firstName, String lastName, String status, String district) {
		Clients client = new Clients()
				.setPhone(phone)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setStatus(status)
				.setDistrict(district);
		return repository.save(client);
	}

	private Clients client1;

	@BeforeEach
	void setup() {
		client1 = createClient("79991112233","Иван", "Иванов", "Постоянный", "Ленинский");
		createClient("79990001122","Алексей", "Смирнов", "Постоянный", "Октябрьский");
		createClient("79990001123","Дмитрий", "Кузнецов",  "Новый", "Первомайский");
	}

	@Test
	@DisplayName("Поиск клиента по номеру телефона")
	void shouldFindByPhone() {
		Optional<Clients> found = repository.findByPhone(client1.getPhone());
		assertThat(found).isPresent();
		assertThat(found.get().getId()).isEqualTo(client1.getId());
	}

	@Test
	@DisplayName("Поиск клиента по несуществующему номеру телефона")
	void shouldNodFindByIncorrectPhone() {
		Optional<Clients> found = repository.findByPhone("70000000000");
		assertThat(found).isEmpty();
	}

	@Test
	@DisplayName("Поиск клиента по имени")
	void shouldFindByFirstName() {
		Iterable<Clients> result = repository.find("%" + client1.getFirstName() + "%");
		assertThat(result).anyMatch(c -> c.getId().equals(client1.getId()));
	}

	@Test
	@DisplayName("Поиск клиента по несуществующему имени")
	void shouldNotFindByIncorrectFirstName() {
		Iterable<Clients> result = repository.find("%test%");
		assertThat(result).isEmpty();
	}

	@Test
	@DisplayName("Поиск клиента по фамилии")
	void shouldFindByLastName() {
		Iterable<Clients> result = repository.find("%" + client1.getFirstName() + "%");
		assertThat(result).anyMatch(c -> c.getId().equals(client1.getId()));
	}

	@Test
	@DisplayName("Поиск клиента по несуществующей фамилии")
	void shouldNotFindByLastName() {
		Iterable<Clients> result = repository.find("%test%");
		assertThat(result).isEmpty();
	}

	@Test
	@DisplayName("Поиск отфильтрованных клиентов")
	void shouldFindByFilter() {
		Iterable<Clients> clientsByNumber = repository.find("%1122%", null, null);
		Iterable<Clients> clientsByStatus = repository.find("%%", new String[] { "Постоянный" }, null);
		Iterable<Clients> clientsByDistricts = repository.find("%%", null, new String[] { "Ленинский" });
		Iterable<Clients> clients = repository.find("%112%", new String[] { "Постоянный" }, new String[] { "Ленинский" });

		assertThat(((Collection<?>) clientsByNumber).size()).isEqualTo(2);
		assertThat(((Collection<?>) clientsByStatus).size()).isEqualTo(2);
		assertThat(((Collection<?>) clientsByDistricts).size()).isEqualTo(1);
		assertThat(((Collection<?>) clients).size()).isEqualTo(1);
	}
}
