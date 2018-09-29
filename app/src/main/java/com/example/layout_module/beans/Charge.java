package com.example.layout_module.beans;

public class Charge {

    private int id;
    private String amount;
    private String lastDate;
    private boolean isPaid;
    private String addedOn;
    private String paidOn;
    private ChargeType chargeType;
    private House house;

    public Charge() {
    }

    public Charge(int id, String amount, String lastDate, boolean isPaid, String addedOn, String paidOn, ChargeType chargeType, House house) {
        this.id = id;
        this.amount = amount;
        this.lastDate = lastDate;
        this.isPaid = isPaid;
        this.addedOn = addedOn;
        this.paidOn = paidOn;
        this.chargeType = chargeType;
        this.house = house;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(String paidOn) {
        this.paidOn = paidOn;
    }

    public String getChargeType() {
        return chargeType.getName();
    }

    public void setChargeType(ChargeType chargeType) {
        this.chargeType = chargeType;
    }

    public int getHouse() {
        return house.getId();
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Charge{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", lastDate='" + lastDate + '\'' +
                ", isPaid=" + isPaid +
                ", addedOn='" + addedOn + '\'' +
                ", paidOn='" + paidOn + '\'' +
                ", chargeType=" + chargeType +
                ", house=" + house +
                '}';
    }
}