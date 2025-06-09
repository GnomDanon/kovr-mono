package ru.kovrochist.platform.mono.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.request.RequestDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.security.user.User;
import ru.kovrochist.platform.mono.type.OrderStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RequestServiceTest {

	@Mock
	private OrderService orderService;

	@Mock
	private ClientService clientService;

	@Mock
	private User user;

	@InjectMocks
	private RequestService requestService;

	private final Long testUserId = 1L;
	private final String testPhone = "79998887766";
	private final String testFirstName = "Иван";
	private final String testLastName = "Иванов";
	private final String testCity = "Москва";
	private final String testAddress = "ул. Пушкина, д.10";
	private final String testComment = "Комментарий";

	private RequestDto testRequestDto;
	private Clients testClient;
	private Orders testOrder;

	@BeforeEach
	void setUp() {
		testRequestDto = new RequestDto()
				.setPhone(testPhone)
				.setFirstName(testFirstName)
				.setLastName(testLastName)
				.setCity(testCity)
				.setAddress(testAddress)
				.setComment(testComment);

		testClient = new Clients()
				.setId(testUserId)
				.setPhone(testPhone)
				.setFirstName(testFirstName)
				.setLastName(testLastName)
				.setCity(testCity)
				.setAddress(testAddress);

		testOrder = new Orders()
				.setId(1L)
				.setClient(testClient)
				.setPhone(testPhone)
				.setCity(testCity)
				.setAddress(testAddress)
				.setComment(testComment)
				.setStatus(OrderStatus.CREATED);

		when(user.getId()).thenReturn(testUserId);
	}

	@Test
	@DisplayName("Создание заявки")
	void create_WithRequestDtoAndExistingUser_ShouldReturnOrderDto() throws ClientDoesNotExistException {
		when(clientService.getById(testUserId)).thenReturn(testClient);
		when(orderService.create(testClient, testPhone, testCity, testAddress, testComment))
				.thenReturn(testOrder);

		OrderDto result = requestService.create(testRequestDto);

		assertNotNull(result);
		verify(clientService).getById(testUserId);
		verify(clientService, never()).create(any(), any(), any(), any(), any());
		verify(orderService).create(testClient, testPhone, testCity, testAddress, testComment);
	}

	@Test
	@DisplayName("Создание заявки с несуществующим клиентом")
	void create_WithRequestDtoAndNullUser_ShouldCreateClientAndReturnOrderDto() throws ClientDoesNotExistException {
		when(user.getId()).thenReturn(null);
		when(clientService.create(testPhone, testFirstName, testLastName, testCity, testAddress))
				.thenReturn(testClient);
		when(orderService.create(testClient, testPhone, testCity, testAddress, testComment))
				.thenReturn(testOrder);

		OrderDto result = requestService.create(testRequestDto);

		assertNotNull(result);
		verify(clientService, never()).getById(any());
		verify(clientService).create(testPhone, testFirstName, testLastName, testCity, testAddress);
		verify(orderService).create(testClient, testPhone, testCity, testAddress, testComment);
	}

	@Test
	@DisplayName("Создание заявки с параметрами")
	void create_WithParametersAndExistingUser_ShouldReturnOrderDto() throws ClientDoesNotExistException {
		when(clientService.getById(testUserId)).thenReturn(testClient);
		when(orderService.create(testClient, testPhone, testCity, testAddress, testComment))
				.thenReturn(testOrder);

		OrderDto result = requestService.create(testPhone, testFirstName, testLastName, testCity, testAddress, testComment);

		assertNotNull(result);
		verify(clientService).getById(testUserId);
		verify(clientService, never()).create(any(), any(), any(), any(), any());
		verify(orderService).create(testClient, testPhone, testCity, testAddress, testComment);
	}

	@Test
	@DisplayName("Создание заявки с параметрами и несуществующим пользователем")
	void create_WithParametersAndNullUser_ShouldCreateClientAndReturnOrderDto() throws ClientDoesNotExistException {
		when(user.getId()).thenReturn(null);
		when(clientService.create(testPhone, testFirstName, testLastName, testCity, testAddress))
				.thenReturn(testClient);
		when(orderService.create(testClient, testPhone, testCity, testAddress, testComment))
				.thenReturn(testOrder);

		OrderDto result = requestService.create(testPhone, testFirstName, testLastName, testCity, testAddress, testComment);

		assertNotNull(result);
		verify(clientService, never()).getById(any());
		verify(clientService).create(testPhone, testFirstName, testLastName, testCity, testAddress);
		verify(orderService).create(testClient, testPhone, testCity, testAddress, testComment);
	}
}
