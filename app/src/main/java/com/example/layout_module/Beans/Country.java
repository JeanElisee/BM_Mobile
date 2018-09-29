package com.example.layout_module.Beans;

public class Country {
    private String iso_code;
    private String name;
    private String isd_code;

    public Country() {
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsd_code() {
        return isd_code;
    }

    public void setIsd_code(String isd_code) {
        this.isd_code = isd_code;
    }

    @Override
    public String toString() {
        return "Country{" +
                "iso_code='" + iso_code + '\'' +
                ", name='" + name + '\'' +
                ", isd_code='" + isd_code + '\'' +
                '}';
    }
}