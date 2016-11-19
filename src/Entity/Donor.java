/*
 * CS4389
 * Data and Application Security
 * Final Project
 * Project Group 4
 * Group Members: Matt Butler, Johnny Edgett, Abdul Wahab
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Matthew
 */
public class Donor {
    private Long donorID;
    private Long locationID;
    private String donorFirst;
    private String donorLast;
    private String donationDate;
    private String donationType;
    private String donationValue;
    private String donationDescription;

    public Long getDonorID() {
        return donorID;
    }

    public void setDonorID(Long donorID) {
        this.donorID = donorID;
    }
    
    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public String getDonorFirst() {
        return donorFirst;
    }

    public void setDonorFirst(String donorFirst) {
        this.donorFirst = donorFirst;
    }

    public String getDonorLast() {
        return donorLast;
    }

    public void setDonorLast(String donorLast) {
        this.donorLast = donorLast;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getDonationValue() {
        return donationValue;
    }

    public void setDonationValue(String donationValue) {
        this.donationValue = donationValue;
    }

    public String getDonationDescription() {
        return donationDescription;
    }

    public void setDonationDescription(String donationDescription) {
        this.donationDescription = donationDescription;
    }
    
    
}
