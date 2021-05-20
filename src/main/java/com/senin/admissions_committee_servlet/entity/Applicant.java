package com.senin.admissions_committee_servlet.entity;

import java.util.List;

public class Applicant {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private ApplicantStatus applicantStatus;
    private ApplicantProfile applicantProfile;
    private List<AdmissionRequest> admissionRequestList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ApplicantStatus getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(ApplicantStatus applicantStatus) {
        this.applicantStatus = applicantStatus;
    }

    public ApplicantProfile getApplicantProfile() {
        return applicantProfile;
    }

    public void setApplicantProfile(ApplicantProfile applicantProfile) {
        this.applicantProfile = applicantProfile;
    }

    public List<AdmissionRequest> getAdmissionRequestList() {
        return admissionRequestList;
    }

    public void setAdmissionRequestList(List<AdmissionRequest> admissionRequestList) {
        this.admissionRequestList = admissionRequestList;
    }
}
