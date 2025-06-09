package ru.kovrochist.platform.mono.repository;

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
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=none")
public class AssignedEmployeeRepositoryTest {

	@Autowired
	private AssignedEmployeeRepository repository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	@DisplayName("Должен сохранять и находить назначенного сотрудника по id заказа и id сотрудника")
	void shouldFindAssignedEmployeeByEmployeeIdAndOrderId() {
		Employees employee = employeeRepository.save(new Employees().setFirstName("Иван"));
		Orders order = orderRepository.save(new Orders());

		AssignedEmployees assigned = new AssignedEmployees()
				.setEmployee(employee)
				.setOrder(order)
				.setComment("Большой ковер");

		repository.save(assigned);

		Optional<AssignedEmployees> result = repository.find(employee.getId(), order.getId());

		assertThat(result).isPresent();
		assertThat(result.get().getComment()).isEqualTo("Большой ковер");
	}

	@Test
	@DisplayName("Должен находить все назначения по id сотрудника")
	void shouldFindAllByEmployeeId() {
		Employees employee = employeeRepository.save(new Employees().setFirstName("Иван"));
		Orders order1 = orderRepository.save(new Orders());
		Orders order2 = orderRepository.save(new Orders());

		repository.save(new AssignedEmployees().setEmployee(employee).setOrder(order1));
		repository.save(new AssignedEmployees().setEmployee(employee).setOrder(order2));

		Iterable<AssignedEmployees> found = repository.findByEmployeeId(employee.getId());

		assertThat(found).hasSize(2);
	}

	@Test
	@DisplayName("Должен находить все назначения по id заказа")
	void shouldFindAllByOrderId() {
		Orders order = orderRepository.save(new Orders());
		Employees employee1 = employeeRepository.save(new Employees().setFirstName("Елена"));
		Employees employee2 = employeeRepository.save(new Employees().setFirstName("Антон"));

		repository.save(new AssignedEmployees().setEmployee(employee1).setOrder(order));
		repository.save(new AssignedEmployees().setEmployee(employee2).setOrder(order));

		Iterable<AssignedEmployees> found = repository.findByOrderId(order.getId());

		assertThat(found).hasSize(2);
	}
}
