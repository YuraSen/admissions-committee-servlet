package com.senin.demo.model.DAO;

import com.senin.demo.model.entity.AdmissionRequest;
import com.senin.demo.model.entity.AdmissionRequestStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AdmissionRequestDAO extends GenericDao<AdmissionRequest>{

    int saveAdmissionRequest(AdmissionRequest admissionRequest) throws SQLException;

    boolean deleteAdmissionRequest(Long id) throws SQLException;

    List<AdmissionRequest> selectAdmissionRequests() throws SQLException;

    boolean changeAdmissionRequestStatus(Long id, AdmissionRequestStatus status) throws SQLException;

    List<AdmissionRequest> selectAdmissionRequestsForApplicantWithId(Long id) throws SQLException;

}
