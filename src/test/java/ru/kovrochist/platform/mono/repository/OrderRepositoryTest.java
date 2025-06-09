package ru.kovrochist.platform.mono.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=update")
public class OrderRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OrderRepository repository;

	private Clients createClient(String phone, String firstName) {
		return clientRepository.save(new Clients().setPhone(phone).setFirstName(firstName));
	}

	private void createOrder(Clients client, OrderStatus orderStatus, DeliveryType deliveryType, String district) {
		repository.save(new Orders().setClient(client).setStatus(orderStatus).setDeliveryType(deliveryType).setDistrict(district));
	}

	private Clients client1;

	@BeforeEach
	void setup() {
		client1 = createClient("79991112233","Иван");
		Clients client3 = createClient("79990001123","Дмитрий");

		createOrder(client1, OrderStatus.CREATED, DeliveryType.DELIVERY, "Ленинский");
		createOrder(client1, OrderStatus.CREATED, DeliveryType.HOME_VISIT, "Октябрьский");
		createOrder(client3, OrderStatus.APPROVED, DeliveryType.DELIVERY, "Первомайский");
	}

	@Test
	@DisplayName("Поиск заказов по идентификатору клиента")
	void shouldFindByClientId() {
		Iterable<Orders> orders = repository.findByClientId(client1.getId());
		assertThat(((Collection<?>) orders).size()).isEqualTo(2);
	}

	@Test
	@DisplayName("Поиск отфильтрованных заказов")
	void shouldFindByFilter() {
		Iterable<Orders> ordersByClientFirstName = repository.find("%Иван%", null, null, null);
		Iterable<Orders> ordersByOrderStatus = repository.find("%%", new OrderStatus[] { OrderStatus.CREATED }, null, null);
		Iterable<Orders> ordersByDeliveryType = repository.find("%%", null, new DeliveryType[] { DeliveryType.DELIVERY }, null);
		Iterable<Orders> ordersByDistricts = repository.find("%%", null, null, new String[] { "Ленинский" });
		Iterable<Orders> orders = repository.find("%Иван%", new OrderStatus[] { OrderStatus.CREATED }, new DeliveryType[] { DeliveryType.HOME_VISIT }, new String[] { "Октябрьский" });

		assertThat(((Collection<?>) ordersByClientFirstName).size()).isEqualTo(2);
		assertThat(((Collection<?>) ordersByOrderStatus).size()).isEqualTo(2);
		assertThat(((Collection<?>) ordersByDeliveryType).size()).isEqualTo(2);
		assertThat(((Collection<?>) ordersByDistricts).size()).isEqualTo(1);
		assertThat(((Collection<?>) orders).size()).isEqualTo(1);
	}
}
