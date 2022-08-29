package com.example.accidenttracking.dto;

public class TimeStatsDto {
    private long totalAccidents;
    private String timeSlot;

    public TimeStatsDto() {}
    public TimeStatsDto(long totalAccidents, String timeSlot) {
        this.totalAccidents = totalAccidents;
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

    public void setTotalAccidents(long totalAccidents) {
        this.totalAccidents = totalAccidents;
    }
}
