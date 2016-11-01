/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
