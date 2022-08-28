package com.example.accidenttracking.pojo;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.accidenttracking.util.GoogleServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class CustomLocation {
    private String streetAddress;
    private String address;
    private String locality;
    private String city;
    private String pinCode;
    private Double longitude;
    private Double latitude;

    public CustomLocation(){}
    public CustomLocation(String address, Double longitude, Double latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public CustomLocation(Context context, String streetAddress, String locality, String city, String pinCode){
        setCity(city);
        setLocality(locality);
        setPinCode(pinCode);
        setStreetAddress(streetAddress);
        setAddress(streetAddress + " " + city + " " + locality + " " + pinCode);
        setCoordinates(context);
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getLocality() {
        return locality;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setCoordinates(Context context){
        LatLng latLng = GoogleServices.getLocationFromAddress(context, address);
        if (latLng != null) {
            setLatitude(latLng.latitude);
            setLongitude(latLng.longitude);
        }
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @NonNull
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, CustomLocation.class);
    }
    // for comparisons
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomLocation location = (CustomLocation) o;
        return Objects.equals(getStreetAddress(), location.getStreetAddress()) &&
                Objects.equals(getAddress(), location.getAddress()) &&
                Objects.equals(getLocality(), location.getLocality()) &&
                Objects.equals(getCity(), location.getCity()) &&
                Objects.equals(getPinCode(), location.getPinCode()) &&
                Objects.equals(getLongitude(), location.getLongitude()) &&
                Objects.equals(getLatitude(), location.getLatitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreetAddress(), getAddress(), getLocality(), getCity(), getPinCode(), getLongitude(), getLatitude());
    }
}
