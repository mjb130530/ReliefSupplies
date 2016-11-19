/*
 * CS4389
 * Data and Application Security
 * Final Project
 * Project Group 4
 * Group Members: Matt Butler, Johnny Edgett, Abdul Wahab
 */
package Entity;

/**
 *
 * @author Matthew
 */
public class Supplies {
    private Long locationID;
    private String supplyName;
    private String supplyType;
    private String supplyQuantity;
    private String supplyDescription;

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }
    
    public String getSupplyName(){
        return supplyName;
    }

    public void setSupplyName(String supplyName){
        this.supplyName = supplyName;
    }
    
    public String getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType;
    }

    public String getSupplyQuantity() {
        return supplyQuantity;
    }

    public void setSupplyQuantity(String supplyQuantity) {
        this.supplyQuantity = supplyQuantity;
    }

    public String getSupplyDescription() {
        return supplyDescription;
    }

    public void setSupplyDescription(String supplyDescription) {
        this.supplyDescription = supplyDescription;
    }
    
    
}
