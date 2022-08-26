package io.github.vikie1.backend.model.analytics;

import javax.persistence.*;

@Entity
public class ByTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
