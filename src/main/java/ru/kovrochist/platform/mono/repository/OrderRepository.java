package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Orders;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {

	@Query("select o from Orders o " +
			"where concat(o.id, ' ', o.client.firstName, ' ', o.client.lastName, ' ', o.client.phone) " +
			"like :filter ")
	Iterable<Orders> find(@Param("filter") String filter);
}
