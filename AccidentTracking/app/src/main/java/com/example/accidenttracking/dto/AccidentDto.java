package com.example.accidenttracking.dto;

import com.example.accidenttracking.pojo.AccidentData;
import com.example.accidenttracking.pojo.CustomLocation;

public class AccidentDto {
    private CustomLocation location;
    private AccidentData accidentData;
    private String reporterName;
    private String reporterEmail;
    private String reporterContact;
    public AccidentDto() {}
    public AccidentDto(String reporterContact, String reporterEmail, String reporterName,
                         CustomLocation location, AccidentData accidentData) {
        this.reporterContact = reporterContact;
        this.reporterEmail = reporterEmail;
        this.reporterName = reporterName;
        this.location = location;
        this.accidentData = accidentData;
    }

    public CustomLocation getLocation() {
        return location;
    }
    public void setLocation(CustomLocation location) {
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
