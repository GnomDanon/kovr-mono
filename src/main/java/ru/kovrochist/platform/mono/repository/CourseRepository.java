package ru.kovrochist.platform.mono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.Course;

import java.util.UUID;

@Repository
public interface CourseRepository extends CrudRepository<Course, UUID> {
}
