package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Orders;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {
	Iterable<Orders> findByClientId(Long clientId);

	@Query("select o from Orders o " +
			"where lower(concat(o.id, ' ', coalesce(o.client.firstName, ''), ' ', coalesce(o.phone, ''))) like lower(:search) " +
			"and (:status is null or o.status in :status) " +
			"and (:deliveryType is null or o.deliveryType in :deliveryType) " +
			"and (:district is null or o.district in :district) ")
	Iterable<Orders> find(@Param("search") String search, @Param("status") OrderStatus[] status, @Param("deliveryType") DeliveryType[] deliveryType, @Param("district") String[] district);
}
