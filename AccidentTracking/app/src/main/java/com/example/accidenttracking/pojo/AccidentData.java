package com.example.accidenttracking.pojo;

import androidx.annotation.NonNull;

import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public class AccidentData {
    private LocalDateTime time;
    private String description;
    private String cause;
    private String causalVehicleType;
    private String causalVehicleGroup;
    private String type;
    private int totalVehiclesInvolved;
    private int totalCasualties;
    private int passengerCasualties;
    private int passersByCasualties;
    private int otherCasualties;
    private String casualtiesDescription;

    public AccidentData(){}
    public AccidentData(LocalDateTime time, String description, String cause, String causalVehicleType,
                        String causalVehicleGroup, String type, int totalVehiclesInvolved, int totalCasualties,
                        int passengerCasualties, int passersByCasualties, int otherCasualties, String casualtiesDescription){
        this.time = time;
        this.description = description;
        this.cause = cause;
        this.causalVehicleType = causalVehicleType;
        this.causalVehicleGroup = causalVehicleGroup;
        this.type = type;
        this.totalVehiclesInvolved = totalVehiclesInvolved;
        this.totalCasualties = totalCasualties;
        this.passengerCasualties = passengerCasualties;
        this.passersByCasualties = passersByCasualties;
        this.otherCasualties = otherCasualties;
        this.casualtiesDescription = casualtiesDescription;
    }

    public LocalDateTime getTime(){return this.time;}
    public String getDescription(){return this.description;}
    public String getCause(){return this.cause;}
    public String getCausalVehicleType(){return this.causalVehicleType;}
    public String getCausalVehicleGroup(){return this.causalVehicleGroup;}
    public String getType(){return this.type;}
    public int getTotalVehiclesInvolved(){return this.totalVehiclesInvolved;}
    public int getTotalCasualties(){return this.totalCasualties;}
    public int getPassengerCasualties(){return this.passengerCasualties;}
    public int getPassersByCasualties(){return this.passersByCasualties;}
    public int getOtherCasualties(){return this.otherCasualties;}
    public String getCasualtiesDescription(){return this.casualtiesDescription;}

    public void setCasualtiesDescription(String casualtiesDescription) {
        this.casualtiesDescription = casualtiesDescription;
    }
    public void setCausalVehicleGroup(String causalVehicleGroup) {
        this.causalVehicleGroup = causalVehicleGroup;
    }
    public void setCausalVehicleType(String causalVehicleType) {
        this.causalVehicleType = causalVehicleType;
    }
    public void setCause(String cause) {
        this.cause = cause;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setOtherCasualties(int otherCasualties) {
        this.otherCasualties = otherCasualties;
    }
    public void setPassengerCasualties(int passengerCasualties) {
        this.passengerCasualties = passengerCasualties;
    }
    public void setPassersByCasualties(int passersByCasualties) {
        this.passersByCasualties = passersByCasualties;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public void setTotalCasualties(int totalCasualties) {
        this.totalCasualties = totalCasualties;
    }
    public void setTotalVehiclesInvolved(int totalVehiclesInvolved) {
        this.totalVehiclesInvolved = totalVehiclesInvolved;
    }
    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, AccidentData.class);
    }
}
