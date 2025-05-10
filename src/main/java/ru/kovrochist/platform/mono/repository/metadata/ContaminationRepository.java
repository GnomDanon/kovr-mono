package ru.kovrochist.platform.mono.repository.metadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.type.Contaminations;
import ru.kovrochist.platform.mono.repository.MetadataRepository;

import java.util.Optional;

@Repository
public interface ContaminationRepository extends MetadataRepository<Contaminations>, JpaRepository<Contaminations, Long> {

	@Query("select c from Contaminations c " +
			"where lower(c.name) = lower(:name) ")
	Optional<Contaminations> findByName(@Param("name") String name);
}
