package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {

	@Query("select o from Orders o " +
			"where concat(o.id, ' ', o.client.firstName, ' ', o.client.lastName, ' ', o.client.phone) " +
			"like :filter ")
	Iterable<Orders> find(@Param("filter") String filter);

	Iterable<Orders> findByClientId(Long clientId);

	@Query("select o from Orders o " +
			"where lower(concat(coalesce(o.id, ''), ' ', coalesce(o.client.firstName, ''), ' ', coalesce(o.phone, ''))) like lower(:search) " +
			"and (:status = null or o.status = :status) " +
			"and (:deliveryType = null or o.deliveryType = :deliveryType) " +
			"and lower(coalesce(o.district, '')) = lower(:district) ")
	Iterable<Orders> find(@Param("search") String search, @Param("status") OrderStatus status, @Param("deliveryType") DeliveryType deliveryType, @Param("district") String district);
}
