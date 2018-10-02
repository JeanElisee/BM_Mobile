package com.example.layout_module.beans;

import java.io.Serializable;

public class OwnerPaperType {
    private int id;
    private String name;

    public OwnerPaperType() {
    }

    public OwnerPaperType(int id, String name) {
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
        return "OwnerPaperType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
