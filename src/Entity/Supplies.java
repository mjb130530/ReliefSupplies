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
