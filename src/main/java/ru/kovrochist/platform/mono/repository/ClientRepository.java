package ru.kovrochist.platform.mono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Client;

import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {

	Iterable<Client> findAllByClientStatusId(UUID clientStatusId);
}
