package io.github.vikie1.backend.repository.analytics;

import io.github.vikie1.backend.model.analytics.ByTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ByTimeRepository extends JpaRepository<ByTime, Long> {
    ByTime findByTimeSlot(String timeSlot);
    boolean existsByTimeSlot(String timeSlot);
    List<ByTime> findByTotalAccidentsGreaterThanEqual(long totalAccidents);
}
