package com.example.layout_module.beans;

import java.io.Serializable;

public class Occupy {
    private int id;
    private String dateOfEntrance;
    private String dateToPayRent;
    private Boolean isActive;
    private String dateToIncreaseRent;
    private Tenant tenant;
    private House house;

    public Occupy() {
    }

    public Occupy(int id, String dateOfEntrance, String dateToPayRent, Boolean isActive, String dateToIncreaseRent, Tenant tenant, House house) {
        super();
        this.id = id;
        this.dateOfEntrance = dateOfEntrance;
        this.dateToPayRent = dateToPayRent;
        this.isActive = isActive;
        this.dateToIncreaseRent = dateToIncreaseRent;
        this.tenant = tenant;
        this.house = house;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfEntrance() {
        return dateOfEntrance;
    }

    public void setDateOfEntrance(String dateOfEntrance) {
        this.dateOfEntrance = dateOfEntrance;
    }

    public String getDateToPayRent() {
        return dateToPayRent;
    }

    public void setDateToPayRent(String dateToPayRent) {
        this.dateToPayRent = dateToPayRent;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getDateToIncreaseRent() {
        return dateToIncreaseRent;
    }

    public void setDateToIncreaseRent(String dateToIncreaseRent) {
        this.dateToIncreaseRent = dateToIncreaseRent;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Occupy{" +
                "id=" + id +
                ", dateOfEntrance='" + dateOfEntrance + '\'' +
                ", dateToPayRent='" + dateToPayRent + '\'' +
                ", isActive=" + isActive +
                ", dateToIncreaseRent='" + dateToIncreaseRent + '\'' +
                ", tenant=" + tenant +
                ", house=" + house +
                '}';
    }
}
