package ru.kovrochist.platform.mono.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.type.Role;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=update")
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	private Employees createEmployee(String phone, String firstName, String lastName, String status, Boolean onShift, Role role) {
		Employees employee = new Employees()
				.setPhone(phone)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setStatus(status)
				.setOnShift(onShift)
				.setRole(role);
		return repository.save(employee);
	}

	private Employees employee1;

	@BeforeEach
	void setup() {
		employee1 = createEmployee("79991112233","Иван", "Иванов", "Работает", true, Role.OPERATOR);
		createEmployee("79990001122","Алексей", "Смирнов", "На больничном", false, Role.COURIER);
		createEmployee("79990001123","Дмитрий", "Кузнецов",  "Отгул", false, Role.MASTER);
	}

	@Test
	@DisplayName("Поиск сотрудника по номеру телефона")
	void shouldFindByPhone() {
		Optional<Employees> found = repository.findByPhone(employee1.getPhone());
		assertThat(found).isPresent();
		assertThat(found.get().getId()).isEqualTo(employee1.getId());
	}

	@Test
	@DisplayName("Поиск сотрудника по несуществующему номеру телефона")
	void shouldNotFoundByIncorrectPhone() {
		Optional<Employees> found = repository.findByPhone("70000000000");
		assertThat(found).isEmpty();
	}

	@Test
	@DisplayName("Поиск отфильтрованных сотрудников")
	void shouldFindByFilter() {
		Iterable<Employees> employeesByNumber = repository.find("%1122%", null, null, null);
		Iterable<Employees> employeesByStatus = repository.find("%%", new String[] { "Работает" }, null, null);
		Iterable<Employees> employeesByOnShift = repository.find("%%", null, true, null);
		Iterable<Employees> employeesByRole = repository.find("%%", null, null, new Role[] { Role.OPERATOR });
		Iterable<Employees> employees = repository.find("%1122%", new String[] { "На больничном" }, false, new Role[] { Role.COURIER });

		assertThat(((Collection<?>) employeesByNumber).size()).isEqualTo(2);
		assertThat(((Collection<?>) employeesByStatus).size()).isEqualTo(1);
		assertThat(((Collection<?>) employeesByOnShift).size()).isEqualTo(1);
		assertThat(((Collection<?>) employeesByRole).size()).isEqualTo(1);
		assertThat(((Collection<?>) employees).size()).isEqualTo(1);
	}
}
