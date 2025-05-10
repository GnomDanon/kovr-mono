package ru.kovrochist.platform.mono.repository;

import java.util.Optional;

public interface MetadataRepository<T> {
	Optional<T> findByName(String name);
}
