package com.example.layout_module.beans;

import java.io.Serializable;

public class TenantPaper {
        private int id;
        private String number;
        private String validityStart;
        private String validityEnd;
        private String submissionDate;
        private Tenant tenant;
        private TenantPaperType tenantPaperType;

        public TenantPaper() {
        }

        public TenantPaper(int id, String number, String validityStart, String validityEnd, String submissionDate, Tenant tenant, TenantPaperType tenantPaperType) {
            this.id = id;
            this.number = number;
            this.validityStart = validityStart;
            this.validityEnd = validityEnd;
            this.submissionDate = submissionDate;
            this.tenant = tenant;
            this.tenantPaperType = tenantPaperType;
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

        public String getSubmissionDate() {
            return submissionDate;
        }

        public void setSubmissionDate(String submissionDate) {
            this.submissionDate = submissionDate;
        }

        public Tenant getTenant() {
            return tenant;
        }

        public void setTenant(Tenant tenant) {
            this.tenant = tenant;
        }

        public TenantPaperType getTenantPaperType() {
            return tenantPaperType;
        }

        public void setTenantPaperType(TenantPaperType tenantPaperType) {
            this.tenantPaperType = tenantPaperType;
        }

    @Override
    public String toString() {
        return "TenantPaper{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", validityStart='" + validityStart + '\'' +
                ", validityEnd='" + validityEnd + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", tenant=" + tenant +
                ", tenantPaperType=" + tenantPaperType +
                '}';
    }
}
