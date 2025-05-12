package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.AssignedEmployees;

import java.util.Optional;

@Repository
public interface AssignedEmployeeRepository extends JpaRepository<AssignedEmployees, Long> {

	@Query("select e from AssignedEmployees e " +
			"where e.employee.id = :employeeId " +
			"and e.order.id = :orderId")
	Optional<AssignedEmployees> find(@Param("employeeId") Long employeeId, @Param("orderId") Long orderId);

	Iterable<AssignedEmployees> findByEmployeeId(Long employeeId);
	Iterable<AssignedEmployees> findByOrderId(Long orderId);
}
