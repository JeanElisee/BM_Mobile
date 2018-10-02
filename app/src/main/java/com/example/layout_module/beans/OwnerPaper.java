package com.example.layout_module.beans;

import java.io.Serializable;

public class OwnerPaper {
    private int id;
    private String number;
    private String submissionDate;
    private String validityStart;
    private String validityEnd;
    private Owner owner;
    private OwnerPaperType ownerPaperType;

    public OwnerPaper() {
    }

    public OwnerPaper(int id, String number, String submissionDate, String validityStart, String validityEnd, Owner owner, OwnerPaperType ownerPaperType) {
        this.id = id;
        this.number = number;
        this.submissionDate = submissionDate;
        this.validityStart = validityStart;
        this.validityEnd = validityEnd;
        this.owner = owner;
        this.ownerPaperType = ownerPaperType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getValidityStart() {
        return validityStart;
    }

    public void setValidityStart(String validityStart) {
        this.validityStart = validityStart;
    }

    public String getValidityEnd() {
        return validityEnd;
    }

    public void setValidityEnd(String validityEnd) {
        this.validityEnd = validityEnd;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public OwnerPaperType getOwnerPaperType() {
        return ownerPaperType;
    }

    public void setOwnerPaperType(OwnerPaperType ownerPaperType) {
        this.ownerPaperType = ownerPaperType;
    }

    @Override
    public String toString() {
        return "OwnerPaper{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", validityStart='" + validityStart + '\'' +
                ", validityEnd='" + validityEnd + '\'' +
                ", owner=" + owner +
                ", ownerPaperType=" + ownerPaperType +
                '}';
    }
}
