package com.example.layout_module.beans;

public class Notice {
    private int id;
    private String object;
    private String message;
    private String submissionDate;
    private Boolean isActive;
    private Owner owner;

    public Notice() {
    }

    public Notice(int id, String object, String message, String submissionDate, Boolean isActive, Owner owner) {
        this.id = id;
        this.object = object;
        this.message = message;
        this.submissionDate = submissionDate;
        this.isActive = isActive;
        this.owner = owner;
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

    public int getOwner() {
        return owner.getId();
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", object='" + object + '\'' +
                ", message='" + message + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", isActive=" + isActive +
                ", owner=" + owner +
                '}';
    }
}
