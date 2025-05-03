package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.ClientStatuses;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientStatusRepository extends CrudRepository<ClientStatuses, UUID> {

	@Query("select c from ClientStatuses c " +
			"where lower(c.name) = lower(:name) ")
	Optional<ClientStatuses> findByName(@Param("name") String name);
}
