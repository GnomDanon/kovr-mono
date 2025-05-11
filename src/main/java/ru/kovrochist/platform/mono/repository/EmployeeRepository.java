package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.type.Role;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employees, Long> {

	@Query("select e from Employees e " +
			"where concat(e.phone, ' ', e.firstName, ' ', e.lastName) like %:filter% ")
	Iterable<Employees> find(@Param("filter") String filter);

	Optional<Employees> findByPhone(String phone);

	@Query("select e from Employees e " +
			"where lower(concat(coalesce(e.phone, ''), ' ', coalesce(e.firstName, ''), ' ', coalesce(e.lastName, ''))) like lower(:search) " +
			"and lower(concat(e.status, '')) like lower(:status) " +
			"and (:onShift = null or e.onShift = :onShift) " +
			"and (:role = null or e.role = :role) ")
	Iterable<Employees> find(@Param("search") String search, @Param("status") String status, @Param("onShift") Boolean onShift, @Param("role") Role role);
}
