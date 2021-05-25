package com.senin.demo.model.DAO;

import com.senin.demo.model.entity.AdmissionRequest;
import com.senin.demo.model.entity.AdmissionRequestStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AdmissionRequestDAO {

    int saveAdmissionRequest(AdmissionRequest admissionRequest);

    boolean deleteAdmissionRequest(Long id) throws SQLException;

    Optional<AdmissionRequest> findAdmissionRequest(Long id);

    boolean updateAdmissionRequest();

    List<AdmissionRequest> selectAdmissionRequests() throws SQLException;

    List<AdmissionRequest> selectAdmissionRequestsForApplicantWithId(Long id);

    List<AdmissionRequest> selectAdmissionRequestsForFacultyWithId(Long id);

    boolean changeAdmissionRequestStatus(Long id, AdmissionRequestStatus status) throws SQLException;

}
