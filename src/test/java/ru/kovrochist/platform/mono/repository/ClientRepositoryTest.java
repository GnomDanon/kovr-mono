package ru.kovrochist.platform.mono.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ru.kovrochist.platform.mono.entity.Clients;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=none")
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

	@Test
	@DisplayName("Поиск клиента по номеру телефона")
	void shouldFindByPhone() {
		Clients saved = createClient("79991112233","Иван", "Иванов", "Постоянный", "Ленинский");
		Optional<Clients> found = repository.findByPhone("79991112233");
		assertThat(found).isPresent();
		assertThat(found.get().getId()).isEqualTo(saved.getId());
	}

	@Test
	@DisplayName("Поиск клиента по несуществующему номеру телефона")
	void shouldNodFindByIncorrectPhone() {
		Clients saved = createClient("79991112233","Иван", "Иванов",  "Постоянный", "Ленинский");
		Optional<Clients> found = repository.findByPhone("70000000000");
		assertThat(found).isEmpty();
	}

	@Test
	@DisplayName("Поиск клиента по имени")
	void shouldFindByFirstName() {
		Clients saved = createClient("79990001122","Алексей", "Смирнов", "Постоянный", "Октябрьский");
		Iterable<Clients> result = repository.find("%алексей%");
		assertThat(result).anyMatch(c -> c.getId().equals(saved.getId()));
	}

	@Test
	@DisplayName("Поиск клиента по несуществующему имени")
	void shouldNotFindByIncorrectFirstName() {
		Clients saved = createClient("79990001122","Алексей", "Смирнов", "Постоянный", "Октябрьский");
		Iterable<Clients> result = repository.find("%test%");
		assertThat(result).isEmpty();
	}

	@Test
	@DisplayName("Поиск клиента по фамилии")
	void shouldFindByLastName() {
		Clients saved = createClient("79990001123","Дмитрий", "Кузнецов",  "Потенциальный", "Первомайский");
		Iterable<Clients> result = repository.find("%кузнецов%");
		assertThat(result).anyMatch(c -> c.getId().equals(saved.getId()));
	}

	@Test
	@DisplayName("Поиск клиента по несуществующей фамилии")
	void shouldNotFindByLastName() {
		Clients saved = createClient("79990001123","Дмитрий", "Кузнецов", "Потенциальный", "Первомайский");
		Iterable<Clients> result = repository.find("%test%");
		assertThat(result).isEmpty();
	}
}
