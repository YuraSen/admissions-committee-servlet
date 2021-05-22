package com.senin.admissions_committee_servlet.DAO;

import com.senin.admissions_committee_servlet.entity.AdmissionRequest;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface AdmissionRequestDAO {

    int saveAdmissionRequest(AdmissionRequest admissionRequest);

    boolean deleteAdmissionRequest(Long id) throws SQLException;

    AdmissionRequest findAdmissionRequest(Long id);

    boolean updateAdmissionRequest();

    List<AdmissionRequest> selectAdmissionRequests() throws SQLException;

    List<AdmissionRequest> selectAdmissionRequestsForApplicantWithId(Long id);

}
