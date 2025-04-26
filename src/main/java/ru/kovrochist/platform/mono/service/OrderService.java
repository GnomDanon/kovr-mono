package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.order.CreateOrderDto;
import ru.kovrochist.platform.mono.dto.order.OrderDto;
import ru.kovrochist.platform.mono.dto.order.UpdateOrderDto;
import ru.kovrochist.platform.mono.entity.Employee;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.exception.order.OrderDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.order.OrderMapper;
import ru.kovrochist.platform.mono.repository.ClientRepository;
import ru.kovrochist.platform.mono.repository.EmployeeRepository;
import ru.kovrochist.platform.mono.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final EmployeeRepository employeeRepository;
	private final ClientRepository clientRepository;

	public OrderDto create(CreateOrderDto orderDto) {
		return new OrderDto();
	}

	public OrderDto update(UpdateOrderDto orders) {
		return new OrderDto();
	}

	public OrderDto changeExecutor(UUID orderId, UUID employeeId) throws DoesNotExistException {
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderDoesNotExistException(orderId));
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeDoesNotExistException(employeeId));
		order.setEmployee(employee);
		orderRepository.save(order);
		return OrderMapper.map(order);
	}

	public List<OrderDto> get() {
		List<OrderDto> orderDtos = new ArrayList<>();
		Iterable<Orders> orders = orderRepository.findAll();
		for (Orders order : orders) {
			orderDtos.add(OrderMapper.map(order));
		}
		return orderDtos;
	}

	public OrderDto get(UUID id) throws DoesNotExistException {
		return OrderMapper.map(orderRepository.findById(id).orElseThrow(() -> new OrderDoesNotExistException(id)));
	}

	public List<OrderDto> getByEmployeeId(UUID id) {
		return orderRepository.findAllByEmployeeId(id).stream().map(OrderMapper::map).toList();
	}

	public List<OrderDto> getByClientId(UUID id) {
		return orderRepository.findAllByClientId(id).stream().map(OrderMapper::map).toList();
	}
}
