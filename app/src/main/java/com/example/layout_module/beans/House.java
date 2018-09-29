package com.example.layout_module.beans;

import java.util.List;

public class House {

    private int id;
    private String doorNo;
    private String floorNo;
    private String rentAmount;
    private String deposit;
    private int occupationType;
    private Building building;
    private HouseType houseType;
    private List<Charge> charges;

    public House() {
        super();
    }

    public House(int id, String doorNo, String floorNo, String rentAmount, String deposit, int occupationType, Building building, HouseType houseType, List<Charge> charges) {
        super();
        this.id = id;
        this.doorNo = doorNo;
        this.floorNo = floorNo;
        this.rentAmount = rentAmount;
        this.deposit = deposit;
        this.occupationType = occupationType;
        this.building = building;
        this.houseType = houseType;
        this.charges = charges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(String rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public int getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(int occupationType) {
        this.occupationType = occupationType;
    }

    public int getBuilding() {
        return building.getId();
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public int getHouseType() {
        return houseType.getId();
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }
}