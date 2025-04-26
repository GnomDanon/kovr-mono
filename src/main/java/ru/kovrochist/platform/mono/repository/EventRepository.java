package ru.kovrochist.platform.mono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Event;

import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {
}
