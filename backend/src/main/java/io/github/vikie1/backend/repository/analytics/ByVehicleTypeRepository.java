package io.github.vikie1.backend.repository.analytics;

import io.github.vikie1.backend.model.analytics.ByVehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ByVehicleTypeRepository extends JpaRepository<ByVehicleType, Long> {
    ByVehicleType findByCarType(String carType);
    boolean existsByCarType(String carType);
    List<ByVehicleType> findByTotalAccidentsGreaterThanEqual(long totalAccidents);
}
