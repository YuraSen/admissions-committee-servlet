package com.senin.admissions_committee_servlet.DAO;

import com.senin.admissions_committee_servlet.entity.AdmissionRequest;

import java.util.Collection;

public interface AdmissionRequestDAO {

    int createAdmissionRequest();

    boolean deleteAdmissionRequest();

    AdmissionRequest findAdmissionRequest();

    boolean updateAdmissionRequest();

    Collection<AdmissionRequest> selectAdmissionRequestsTO();

}
