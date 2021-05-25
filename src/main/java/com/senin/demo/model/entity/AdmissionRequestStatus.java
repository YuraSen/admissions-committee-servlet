package com.senin.demo.model.entity;

public enum AdmissionRequestStatus {
    NEW, APPROVED, REJECTED;

    private static AdmissionRequestStatus[] values = AdmissionRequestStatus.values();

    public static AdmissionRequestStatus getAdmissionRequestStatus(int i) {
        return values[i - 1];
    }
}
