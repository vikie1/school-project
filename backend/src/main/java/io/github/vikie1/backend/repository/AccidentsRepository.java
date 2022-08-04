package io.github.vikie1.backend.repository;

import io.github.vikie1.backend.model.AccidentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccidentsRepository extends JpaRepository<AccidentModel, Long> {
}
