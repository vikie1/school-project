package io.github.vikie1.backend.model.analytics;

import io.github.vikie1.backend.model.AccidentModel;

import javax.persistence.*;

@Entity
public class ByTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false)
    private Long id;
    private long totalAccidents;
    private String city;
    private String locality;
    @Column(nullable = false, unique = true)
    private String timeSlot;

    public ByTime() {}
    public ByTime(long totalAccidents, String city, String locality, String timeSlot) {
        this.totalAccidents = totalAccidents;
        this.city = city;
        this.locality = locality;
        this.timeSlot = timeSlot;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public long getTotalAccidents() {
        return totalAccidents;
    }

    public String getCity() {
        return city;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTotalAccidents(long totalAccidents) {
        this.totalAccidents = totalAccidents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
