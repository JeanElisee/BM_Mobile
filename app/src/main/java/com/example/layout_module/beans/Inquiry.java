package com.example.layout_module.beans;

public class Inquiry {
    private int id;
    private String object;
    private String message;
    private String submissionDate;
    private Boolean isActive;
    private Tenant tenant;

    public Inquiry() {
    }

    public Inquiry(int id, String object, String message, String submissionDate, Boolean isActive, Tenant tenant) {
        this.id = id;
        this.object = object;
        this.message = message;
        this.submissionDate = submissionDate;
        this.isActive = isActive;
        this.tenant = tenant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public int getTenant() {
        return tenant.getId();
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return "Inquiry{" +
                "id=" + id +
                ", object='" + object + '\'' +
                ", message='" + message + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", isActive=" + isActive +
                ", tenant=" + tenant +
                '}';
    }
}
