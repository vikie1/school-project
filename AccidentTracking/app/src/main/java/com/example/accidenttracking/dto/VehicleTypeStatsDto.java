package com.example.accidenttracking.dto;

public class VehicleTypeStatsDto {
    private long totalAccidents;
    private String carType;

    public VehicleTypeStatsDto() {}
    public VehicleTypeStatsDto(long totalAccidents, String carType) {
        this.totalAccidents = totalAccidents;
        this.carType = carType;
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
