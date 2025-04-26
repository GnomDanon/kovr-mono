package ru.kovrochist.platform.mono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.ContentType;

import java.util.UUID;

@Repository
public interface ContentTypeRepository extends CrudRepository<ContentType, UUID> {
}
