package ru.kovrochist.platform.mono.repository.metadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.type.Products;
import ru.kovrochist.platform.mono.repository.MetadataRepository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MetadataRepository<Products>, JpaRepository<Products, Long> {

	@Query("select p from Products p " +
			"where lower(p.name) = lower(:name) ")
	Optional<Products> findByName(@Param("name") String name);
}
