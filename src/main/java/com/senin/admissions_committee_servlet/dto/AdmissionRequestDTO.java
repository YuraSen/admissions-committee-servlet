package com.senin.admissions_committee_servlet.dto;

import com.senin.admissions_committee_servlet.entity.AdmissionRequestStatus;
import com.senin.admissions_committee_servlet.entity.Applicant;
import com.senin.admissions_committee_servlet.entity.Faculty;

import java.time.LocalDateTime;

public class AdmissionRequestDTO {
    private Applicant applicant;
    private Faculty faculty;
    private AdmissionRequestStatus admissionRequestStatus;
    private Long id;
    private String firstName;
    private String lastName;
    private String facultyName;
    private LocalDateTime creationDateTime;
    private Integer requiredSubject1Grade;
    private Integer requiredSubject2Grade;
    private Integer requiredSubject3Grade;
    private Long applicantId;
    private  Long facultyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
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

    public Integer getRequiredSubject1Grade() {
        return requiredSubject1Grade;
    }

    public void setRequiredSubject1Grade(Integer requiredSubject1Grade) {
        this.requiredSubject1Grade = requiredSubject1Grade;
    }

    public Integer getRequiredSubject2Grade() {
        return requiredSubject2Grade;
    }

    public void setRequiredSubject2Grade(Integer requiredSubject2Grade) {
        this.requiredSubject2Grade = requiredSubject2Grade;
    }

    public Integer getRequiredSubject3Grade() {
        return requiredSubject3Grade;
    }

    public void setRequiredSubject3Grade(Integer requiredSubject3Grade) {
        this.requiredSubject3Grade = requiredSubject3Grade;
    }

    public AdmissionRequestStatus getAdmissionRequestStatus() {
        return admissionRequestStatus;
    }

    public void setAdmissionRequestStatus(AdmissionRequestStatus admissionRequestStatus) {
        this.admissionRequestStatus = admissionRequestStatus;
    }
}
