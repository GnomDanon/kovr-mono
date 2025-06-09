package ru.kovrochist.platform.mono.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ru.kovrochist.platform.mono.entity.AssignedEmployees;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.entity.Orders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=update")
public class AssignedEmployeeRepositoryTest {

	@Autowired
	private AssignedEmployeeRepository repository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private OrderRepository orderRepository;

	private Long employee1;

	private Long order1;
	private Long order2;

	@BeforeEach
	void setup() {
		Employees employee1 = employeeRepository.save(new Employees().setFirstName("Иван"));
		Employees employee2 = employeeRepository.save(new Employees().setLastName("Петр"));

		this.employee1 = employee1.getId();

		Orders order1 = orderRepository.save(new Orders());
		Orders order2 = orderRepository.save(new Orders());

		this.order1 = order1.getId();
		this.order2 = order2.getId();

		repository.save(new AssignedEmployees().setEmployee(employee1).setOrder(order1).setComment("Первый"));
		repository.save(new AssignedEmployees().setEmployee(employee1).setOrder(order2).setComment("Второй"));
		repository.save(new AssignedEmployees().setEmployee(employee2).setOrder(order2).setComment("Третий"));
	}

	@Test
	@DisplayName("Сохранение и получение назначенного сотрудника")
	void shouldFindAssignedEmployeeByEmployeeIdAndOrderId() {
		Optional<AssignedEmployees> result = repository.find(employee1, order1);

		assertThat(result).isPresent();
		assertThat(result.get().getComment()).isEqualTo("Первый");
	}

	@Test
	@DisplayName("Получение назначенных заказов")
	void shouldFindAllByEmployeeId() {
		Iterable<AssignedEmployees> found = repository.findByEmployeeId(employee1);
		assertThat(found).hasSize(2);
	}

	@Test
	@DisplayName("Получение назначенных сотрудников")
	void shouldFindAllByOrderId() {
		Iterable<AssignedEmployees> found = repository.findByOrderId(order2);
		assertThat(found).hasSize(2);
	}
}
