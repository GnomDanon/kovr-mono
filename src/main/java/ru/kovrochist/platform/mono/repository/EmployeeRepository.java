package ru.kovrochist.platform.mono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Employee;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {

	Iterable<Employee> findAllByRoleId(UUID roleId);
}
