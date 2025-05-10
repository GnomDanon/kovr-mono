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
			"where concat(c.phone, ' ', c.firstName, ' ', c.lastName) like :filter ")
	Iterable<Clients> find(@Param("filter") String filter);
}
