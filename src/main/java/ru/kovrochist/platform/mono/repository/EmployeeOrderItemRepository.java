package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.EmployeeOrderItems;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeOrderItemRepository extends JpaRepository<EmployeeOrderItems, Long> {

	@Query("select e from EmployeeOrderItems e " +
			"where e.employee.id = :employeeId " +
			"and e.order.id = :orderId")
	Optional<EmployeeOrderItems> find(@Param("orderId") Long employeeId, @Param("employeeId") Long orderId);

	Iterable<EmployeeOrderItems> findByEmployeeId(Long employeeId);
	Iterable<EmployeeOrderItems> findByOrderId(Long orderId);
}
