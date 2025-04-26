package ru.kovrochist.platform.mono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Orders;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Orders, UUID> {
	List<Orders> findAllByEmployeeId(UUID employeeId);
	List<Orders> findAllByClientId(UUID clientId);
}
