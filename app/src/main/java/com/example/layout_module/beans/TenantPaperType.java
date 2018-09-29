package com.example.layout_module.beans;

import java.io.Serializable;

public class TenantPaperType {
    private int id;
    private String name;

    public TenantPaperType() {
        super();
    }

    public TenantPaperType(int id, String name) {
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
        return "TenantPaperType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
