package io.github.vikie1.backend.model.analytics;

import io.github.vikie1.backend.model.AccidentModel;

import javax.persistence.*;

@Entity
public class ByTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false)
    private Long id;
    private String timeIntervalStart;
    private String timeIntervalEnd;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "timeAnalytics", cascade = CascadeType.ALL)
    private AccidentModel accidentModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
