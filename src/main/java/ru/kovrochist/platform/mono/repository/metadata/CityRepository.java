package ru.kovrochist.platform.mono.repository.metadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.type.Cities;
import ru.kovrochist.platform.mono.repository.MetadataRepository;

import java.util.Optional;

@Repository
public interface CityRepository extends MetadataRepository<Cities>, JpaRepository<Cities, Long> {

	@Query("select c from Cities c " +
			"where lower(c.name) = lower(:name) ")
	Optional<Cities> findByName(@Param("name") String name);
}
