package com.example.layout_module.beans;

public class ChargeType {
    public int id;
    public String name;

    public ChargeType() {
    }

    public ChargeType(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "ChargeType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
