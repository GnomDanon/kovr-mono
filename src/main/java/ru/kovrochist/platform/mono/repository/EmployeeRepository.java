package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Employees;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employees, Long> {

	@Query("select e from Employees e " +
			"where concat(e.phone, ' ', e.firstName, ' ', e.lastName) like :filter ")
	Iterable<Employees> find(@Param("filter") String filter);

	Optional<Employees> findByPhone(String phone);
}
