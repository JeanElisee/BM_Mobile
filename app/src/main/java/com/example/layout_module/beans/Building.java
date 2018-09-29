package com.example.layout_module.beans;

import java.io.Serializable;

public class Building implements Serializable {

    private int id;
    private String name;
    private int noFloor;
    private String addedOn;
    private String address;
    private Owner owner;

    public Building() {
        super();
    }

    public Building(int id, String name, int noFloor, String addedOn, String address, Owner owner) {
        super();
        this.id = id;
        this.name = name;
        this.noFloor = noFloor;
        this.addedOn = addedOn;
        this.address = address;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoFloor() {
        return noFloor;
    }

    public void setNoFloor(int noFloor) {
        this.noFloor = noFloor;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOwner() {
        return owner.getId();
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", noFloor=" + noFloor +
                ", addedOn='" + addedOn + '\'' +
                ", address='" + address + '\'' +
                ", owner=" + owner +
                '}';
    }
}