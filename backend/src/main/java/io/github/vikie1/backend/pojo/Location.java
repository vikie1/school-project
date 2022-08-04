package io.github.vikie1.backend.pojo;

import javax.persistence.Embeddable;

/**
 * Location.java Object, Stores details that can be used to find
 *  the location or at least a rough estimate of the locale
 */
@Embeddable
public class Location {
    private String country;
    private String address;
    private String streetAddress;
    private String locality;
    private String city;
    private String pinCode;
    private Double longitude;
    private Double latitude;

    public Location() {}
    public Location(String address, String streetAddress, String locality, String country,
                    String city, String pinCode, Double longitude, Double latitude) {
        this.address = address;
        this.streetAddress = streetAddress;
        this.locality = locality;
        this.city = city;
        this.pinCode = pinCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.country = country;
    }

    public String getAddress() {return address();}
    public void setAddress(String address) {this.address = address;}
    public String getStreetAddress() {return this.streetAddress;}
    public void setStreetAddress(String streetAddress) {this.streetAddress = streetAddress;}
    public String getLocality() {return this.locality;}
    public void setLocality(String locality) {this.locality = locality;}
    public String getCity() {return this.city;}
    public void setCity(String city) {this.city = city;}
    public String getPinCode() {return this.pinCode;}
    public void setPinCode(String pinCode) {this.pinCode = pinCode;}
    public Double getLongitude() {return this.longitude;}
    public void setLongitude(Double longitude) {this.longitude = longitude;}
    public Double getLatitude() {return this.latitude;}
    public void setLatitude(Double latitude) {this.latitude = latitude;}
    public String getCountry() {return this.country;}
    public void setCountry(String country) {this.country = country;}
    public String address(){
        return address != null && !address.isBlank()? address : streetAddress + " " + city + " " + locality + " " + pinCode;
    }
}
