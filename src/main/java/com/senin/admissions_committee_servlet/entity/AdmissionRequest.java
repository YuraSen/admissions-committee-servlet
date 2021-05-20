package com.senin.admissions_committee_servlet.entity;

import java.time.LocalDateTime;

public class AdmissionRequest {
    private Long id;
    private AdmissionRequestStatus admissionRequestStatus;
    private Long applicantId;
    private Long facultyId;
    private int requiredSubject1Grade;
    private int requiredSubject2Grade;
    private int requiredSubject3Grade;

    private LocalDateTime creationDateTime;

    public static AdmissionRequest createAdmissionRequest() {
        return new AdmissionRequest();

    }

    public int getSumOfGrades() {
        return getRequiredSubject1Grade() + getRequiredSubject2Grade() + getRequiredSubject3Grade();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmissionRequestStatus getAdmissionRequestStatus() {
        return admissionRequestStatus;
    }

    public void setAdmissionRequestStatus(AdmissionRequestStatus admissionRequestStatus) {
        this.admissionRequestStatus = admissionRequestStatus;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public int getRequiredSubject1Grade() {
        return requiredSubject1Grade;
    }

    public void setRequiredSubject1Grade(int requiredSubject1Grade) {
        this.requiredSubject1Grade = requiredSubject1Grade;
    }

    public int getRequiredSubject2Grade() {
        return requiredSubject2Grade;
    }

    public void setRequiredSubject2Grade(int requiredSubject2Grade) {
        this.requiredSubject2Grade = requiredSubject2Grade;
    }

    public int getRequiredSubject3Grade() {
        return requiredSubject3Grade;
    }

    public void setRequiredSubject3Grade(int requiredSubject3Grade) {
        this.requiredSubject3Grade = requiredSubject3Grade;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
