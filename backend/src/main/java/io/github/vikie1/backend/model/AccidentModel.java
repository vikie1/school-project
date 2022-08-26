package io.github.vikie1.backend.model;

import io.github.vikie1.backend.model.analytics.ByTime;
import io.github.vikie1.backend.pojo.AccidentData;
import io.github.vikie1.backend.pojo.Location;

import javax.persistence.*;

@Entity
public class AccidentModel {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) @Column(name = "id", nullable = false)
    private Long id;
    @Embedded
    private Location location;
    @Embedded
    private AccidentData accidentData;
    private String reporterName;
    private String reporterEmail;
    private String reporterContact;

    public AccidentModel() {}
    public AccidentModel(String reporterContact, String reporterEmail, String reporterName,
                         Location location, AccidentData accidentData) {
        this.reporterContact = reporterContact;
        this.reporterEmail = reporterEmail;
        this.reporterName = reporterName;
        this.location = location;
        this.accidentData = accidentData;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public AccidentData getAccidentData() {
        return accidentData;
    }
    public void setAccidentData(AccidentData accidentData) {
        this.accidentData = accidentData;
    }

    public String getReporterContact() {
        return reporterContact;
    }
    public void setReporterContact(String reporterContact) {
        this.reporterContact = reporterContact;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }
    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }

    public String getReporterName() {
        return reporterName;
    }
    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }
}
