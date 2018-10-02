package com.example.layout_module.beans;

public class HouseType {
    private int id;
    private String name;

    public HouseType() {
    }

    public HouseType(int id, String name) {
        super();
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
        return "HouseType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}