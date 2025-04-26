package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Address;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends CrudRepository<Address, UUID> {

	@Query("select a from Address a " +
			"where a.region = :region " +
			"and a.city = :city " +
			"and a.street = :street " +
			"and a.houseNumber = :houseNumber " +
			"and a.flatNumber = :flatNumber " +
			"and a.floorNumber = :floorNumber")
	Optional<Address> find(
			@Param("region") String region,
			@Param("city") String city,
			@Param("street") String street,
			@Param("houseNumber") Integer houseNumber,
			@Param("flatNumber") Integer flatNumber,
			@Param("floorNumber") Integer floorNumber
	);
}
