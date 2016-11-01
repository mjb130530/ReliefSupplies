/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Matthew
 */
public class Location {
    private Long locationID;
    private String locationState;
    private String county;
    private String city;
    private String street;
    private String zipcode;
    private Long cityTaxes;
    private Long stateTaxes;
    private Long rent;
    private Long electricity;
    private Long water;

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Long getCityTaxes() {
        return cityTaxes;
    }

    public void setCityTaxes(Long cityTaxes) {
        this.cityTaxes = cityTaxes;
    }

    public Long getStateTaxes() {
        return stateTaxes;
    }

    public void setStateTaxes(Long stateTaxes) {
        this.stateTaxes = stateTaxes;
    }

    public Long getRent() {
        return rent;
    }

    public void setRent(Long rent) {
        this.rent = rent;
    }

    public Long getElectricity() {
        return electricity;
    }

    public void setElectricity(Long electricity) {
        this.electricity = electricity;
    }

    public Long getWater() {
        return water;
    }

    public void setWater(Long water) {
        this.water = water;
    }
    
    
}
