package ru.kovrochist.platform.mono.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.kovrochist.platform.mono.dto.employee.AssignedEmployeeDto;
import ru.kovrochist.platform.mono.dto.order.AssignDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.RescheduleDto;
import ru.kovrochist.platform.mono.dto.order.StatusWrapper;
import ru.kovrochist.platform.mono.dto.order.UpdateCommentDto;
import ru.kovrochist.platform.mono.dto.order.UpdateOrderItemDto;
import ru.kovrochist.platform.mono.exception.Advice;
import ru.kovrochist.platform.mono.filter.OrderFilter;
import ru.kovrochist.platform.mono.service.OrderService;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	@Mock
	private OrderService orderService;

	@InjectMocks
	private OrderController orderController;

	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).setControllerAdvice(new Advice()).build();
	}

	@Test
	@DisplayName("Получение заказов")
	void shouldFetchOrders() throws Exception {
		List<OrderDto> orders = List.of(new OrderDto(1L), new OrderDto(2L));
		when(orderService.fetchOrder()).thenReturn(orders);

		mockMvc.perform(get("/orders"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orders)));
	}

	@Test
	@DisplayName("Получение отфильтрованных заказов")
	void shouldFetchFilteredOrders() throws Exception {
		List<OrderDto> orders = List.of(new OrderDto(1L));
		when(orderService.getOrders(any(OrderFilter.class))).thenReturn(orders);

		mockMvc.perform(get("/orders/filter").param("search", "1"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orders)));
	}

	@Test
	@DisplayName("Получение заказа по идентификатору")
	void shouldGetOrderById() throws Exception {
		OrderDto order = new OrderDto(1L);
		when(orderService.getOrderById(1L)).thenReturn(order);

		mockMvc.perform(get("/orders/1"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(order)));
	}

	@Test
	@DisplayName("Получение заказов сотрудника")
	void shouldGetOrdersByEmployee() throws Exception {
		List<OrderDto> orders = List.of(new OrderDto(1L));
		when(orderService.getEmployeeOrders(1L)).thenReturn(orders);

		mockMvc.perform(get("/orders/employee/1"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orders)));
	}

	@Test
	@DisplayName("Получение заказов клиента")
	void shouldGetOrdersByClient() throws Exception {
		List<OrderDto> orders = List.of(new OrderDto(1L));
		when(orderService.getClientOrders(1L)).thenReturn(orders);

		mockMvc.perform(get("/orders/client/1"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orders)));
	}

	@Test
	@DisplayName("Обновление статуса заказа")
	void shouldUpdateOrderStatus() throws Exception {
		StatusWrapper status = new StatusWrapper("В работе");
		OrderDto updated = new OrderDto(1L);
		when(orderService.updateOrderStatus(1L, "В работе")).thenReturn(updated);

		mockMvc.perform(patch("/orders/1/status")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(status)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Создание заказа")
	void shouldCreateOrder() throws Exception {
		OrderDto created = new OrderDto(1L);
		when(orderService.createOrder(created)).thenReturn(created);

		mockMvc.perform(post("/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(created)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(created)));
	}

	@Test
	@DisplayName("Удаление заказа")
	void shouldDeleteOrder() throws Exception {
		mockMvc.perform(delete("/orders/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("ok"));
	}

	@Test
	@DisplayName("Обновление заказа")
	void shouldUpdateOrder() throws Exception {
		OrderDto updated = new OrderDto(1L);
		when(orderService.update(any(OrderDto.class))).thenReturn(updated);

		mockMvc.perform(patch("/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(new OrderDto(1L))))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Обновление услуг")
	void shouldUpdateOrderItemServices() throws Exception {
		UpdateOrderItemDto dto = new UpdateOrderItemDto();
		OrderDto updated = new OrderDto();
		when(orderService.updateOrderItemServices(1L, dto)).thenReturn(updated);

		mockMvc.perform(patch("/orders/1/services")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Назначение сотрудника на заказ")
	void shouldAssignEmployeeToOrder() throws Exception {
		AssignDto assignDto = new AssignDto().setEmployeeId(1L);
		OrderDto updated = new OrderDto();
		when(orderService.assignEmployeeToOrder(1L, 1L)).thenReturn(updated);

		mockMvc.perform(post("/orders/1/assignees")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(assignDto)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Снятие сотрудника с заказа")
	void shouldDeAssignEmployee() throws Exception {
		mockMvc.perform(delete("/orders/1/assignees/2"))
				.andExpect(status().isOk())
				.andExpect(content().string("ok"));
	}

	@Test
	@DisplayName("Обновление комментария")
	void shouldUpdateEmployeeComment() throws Exception {
		UpdateCommentDto dto = new UpdateCommentDto().setEmployeeId(1L).setComment("Комментарий");
		AssignedEmployeeDto updated = new AssignedEmployeeDto().setEmployeeId(1L).setComment("Комментарий");
		when(orderService.updateEmployeeComment(1L, 1L, "Комментарий")).thenReturn(updated);

		mockMvc.perform(patch("/orders/1/employee-comment")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}

	@Test
	@DisplayName("Обновление информации о доставке")
	void shouldRescheduleOrder() throws Exception {
		Date now = new Date();
		RescheduleDto dto = new RescheduleDto();
		dto.setDeliveryDays(new String[] { "пн", "вт" });
		dto.setDeliveryTimeStart(now);
		dto.setDeliveryTimeEnd(now);
		OrderDto updated = new OrderDto(1L);
		when(orderService.rescheduleOrder(1L, dto.getDeliveryDays(), dto.getDeliveryTimeStart(), dto.getDeliveryTimeEnd())).thenReturn(updated);

		mockMvc.perform(patch("/orders/1/schedule")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updated)));
	}
}
