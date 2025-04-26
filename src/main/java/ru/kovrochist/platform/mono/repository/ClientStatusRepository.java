package ru.kovrochist.platform.mono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.ClientStatus;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientStatusRepository extends CrudRepository<ClientStatus, UUID> {

	Optional<ClientStatus> findByName(String name);
}
