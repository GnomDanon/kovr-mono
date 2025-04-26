package ru.kovrochist.platform.mono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.CourseDataCompletingInfo;

import java.util.UUID;

@Repository
public interface CourseDataCompletingInfoRepository extends CrudRepository<CourseDataCompletingInfo, UUID> {
}
