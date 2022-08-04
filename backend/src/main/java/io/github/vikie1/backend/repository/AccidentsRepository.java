package io.github.vikie1.backend.repository;

import io.github.vikie1.backend.model.AccidentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AccidentsRepository extends JpaRepository<AccidentModel, Long> {
    default List<AccidentModel> findByAccidentDataTime(LocalDate date){
        return findByAccidentDataTimeBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    List<AccidentModel> findByAccidentDataTimeBetween(LocalDateTime from, LocalDateTime to);
}
