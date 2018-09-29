package com.example.layout_module.beans;

public class AddBuilding {

    private String name;
    private String noFloor;
    private String address;
    private Owner owner;

    public AddBuilding() {
    }

    public AddBuilding(String name, String no_floor, String address, Owner owner) {
        this.name = name;
        this.noFloor = no_floor;
        this.address = address;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo_floor() {
        return noFloor;
    }

    public void setNo_floor(String no_floor) {
        this.noFloor = no_floor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "AddBuilding{" +
                "name='" + name + '\'' +
                ", no_floor='" + noFloor + '\'' +
                ", address='" + address + '\'' +
                ", owner=" + owner +
                '}';
    }
}
