package io.github.vikie1.backend.repository;

import io.github.vikie1.backend.model.AnalyticsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsRepository extends JpaRepository<AnalyticsModel, Long> {
}
