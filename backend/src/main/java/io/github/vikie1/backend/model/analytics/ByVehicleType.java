package io.github.vikie1.backend.model.analytics;

import javax.persistence.*;

@Entity
public class ByVehicleType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false)
    private Long id;

    private long totalAccidents;
    private String carType;

    public ByVehicleType() {}
    public ByVehicleType(long totalAccidents, String carType) {
        this.totalAccidents = totalAccidents;
        this.carType = carType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTotalAccidents() {
        return totalAccidents;
    }

    public void setTotalAccidents(long totalAccidents) {
        this.totalAccidents = totalAccidents;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
