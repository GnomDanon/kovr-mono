package ru.kovrochist.platform.mono.repository.metadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.type.Districts;
import ru.kovrochist.platform.mono.repository.MetadataRepository;

import java.util.Optional;

@Repository
public interface DistrictRepository extends MetadataRepository<Districts>, JpaRepository<Districts, Long> {

	@Query("select d from Districts d " +
			"where lower(d.name) = lower(:name) ")
	Optional<Districts> findByName(@Param("name") String name);
}
