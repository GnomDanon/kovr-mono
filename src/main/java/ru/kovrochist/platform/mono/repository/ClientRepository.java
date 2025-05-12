package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Clients;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Clients, Long> {

	Optional<Clients> findByPhone(String phone);

	@Query("select c from Clients c " +
			"where lower(concat(coalesce(c.phone, '') , ' ', coalesce(c.firstName, '') , ' ', coalesce(c.lastName, ''))) like lower(:filter) ")
	Iterable<Clients> find(@Param("filter") String filter);

	@Query("select c from Clients c " +
			"where lower(concat(coalesce(c.phone, ''), ' ', coalesce(c.firstName, ''), ' ', coalesce(c.lastName, ''))) like lower(:search) " +
			"and (:status is null or c.status in :status) " +
			"and (:district is null or c.district in :district)")
	Iterable<Clients> find(@Param("search") String search, @Param("status") String[] status, @Param("district") String[] district);
}
