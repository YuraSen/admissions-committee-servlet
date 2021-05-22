package com.senin.admissions_committee_servlet.entity;

public enum ApplicantStatus {
    ACTIVE, BLOCKED;

    public String getName() {
        return name();
    }

    private static ApplicantStatus[] values = ApplicantStatus.values();

    public static ApplicantStatus getApplicantStatus(int i) {
        return values[i];
    }
}
