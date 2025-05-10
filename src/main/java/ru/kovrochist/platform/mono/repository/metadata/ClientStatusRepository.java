package ru.kovrochist.platform.mono.repository.metadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.type.ClientStatuses;
import ru.kovrochist.platform.mono.repository.MetadataRepository;

import java.util.Optional;

@Repository
public interface ClientStatusRepository extends MetadataRepository<ClientStatuses>, JpaRepository<ClientStatuses, Long> {

	@Query("select c from ClientStatuses c " +
			"where lower(c.name) = lower(:name) ")
	Optional<ClientStatuses> findByName(@Param("name") String name);
}
