package io.github.vikie1.backend.model;

import javax.persistence.*;

@Entity
public class AnalyticsModel {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
