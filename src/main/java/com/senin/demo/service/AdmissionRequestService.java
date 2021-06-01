package com.senin.demo.service;

import com.senin.demo.exception.CanNotFindRequestById;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.DAO.AdmissionRequestDAO;
import com.senin.demo.model.DAO.DAOFactory;
import com.senin.demo.model.entity.AdmissionRequest;
import com.senin.demo.model.entity.AdmissionRequestStatus;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.model.entity.StatementElement;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


public class AdmissionRequestService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);
    private static final String REPORT_TEMPLATE_FILE_NAME = "JasperDesign.jrxml";


    public void changeAdmissionRequestStatus(Long admissionRequestId, AdmissionRequestStatus newAdmissionRequestStatus) {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            dao.changeAdmissionRequestStatus(admissionRequestId, newAdmissionRequestStatus);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public AdmissionRequest findById(Long admissionRequestId) {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()){
            return dao.findById(admissionRequestId)
                    .orElseThrow(() -> new CanNotFindRequestById("Can not find request by id: " + admissionRequestId));
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public List<AdmissionRequest> findAll() {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            return dao.findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }

    }

    public void create(AdmissionRequest admissionRequest) {

        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            dao.create(admissionRequest);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public List<AdmissionRequest> selectAdmissionRequestsForApplicantWithId(Long id) {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            return dao.selectAdmissionRequestsForApplicantWithId(id);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not get requests for applicant");
        }
    }

    public void delete(Long id) {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            dao.delete(id);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not delete request with id:" + id);
        }
    }

    private List<StatementElement> getStatementElements(List<AdmissionRequest> admissionRequests) {
        List<StatementElement> statementElementList = new ArrayList<>();

        for (int i = 0; i < admissionRequests.size(); i++) {
            StatementElement statementElement = new StatementElement();
            statementElement.setFacultyName(admissionRequests.get(i).getFaculty().getNameEn());
            statementElement.setFirstName(admissionRequests.get(i).getApplicant().getApplicantProfile().getFirstName());
            statementElement.setLastName(admissionRequests.get(i).getApplicant().getApplicantProfile().getLastName());
            statementElement.setEmail(admissionRequests.get(i).getApplicant().getApplicantProfile().getEmail());
            statementElement.setGrade(admissionRequests.get(i).getSumOfGrades());
            statementElement.setContactNumber(admissionRequests.get(i).getApplicant().getApplicantProfile().getPhoneNumber());
            statementElement.setStatus((i < admissionRequests.get(i).getFaculty().getBudgetCapacity() ? "Budget" : "Contract"));

            statementElementList.add(statementElement);

        }
        return statementElementList;
    }

    public byte[] finalizeStatement(Faculty faculty) {
        List<AdmissionRequest> admissionRequestsListSorted = getSortedListOfRequestForFaculty(faculty);
        List<StatementElement> statementElementList = getStatementElements(admissionRequestsListSorted);

        ByteArrayOutputStream outputStream= null;
        try {
            outputStream = createPdfReport(statementElementList);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public List<AdmissionRequest> getSortedListOfRequestForFaculty(Faculty faculty) {
        return faculty.getAdmissionRequestList()
                .stream()
                .filter(x -> x.getAdmissionRequestStatus() == AdmissionRequestStatus.APPROVED)
                .sorted(
                        Comparator.comparingInt(AdmissionRequest::getSumOfGrades).reversed()
                                .thenComparing(AdmissionRequest::getCreationDateTime))
                .limit(faculty.getTotalCapacity())
                .collect(Collectors.toList());
    }


    private ByteArrayOutputStream createPdfReport(final List<StatementElement> statementElementList) throws JRException, FileNotFoundException, URISyntaxException {
        File templateFile = null;
        URL resource = getClass().getClassLoader().getResource(REPORT_TEMPLATE_FILE_NAME);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            templateFile = new File(resource.toURI());
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(statementElementList);
        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
       ByteArrayOutputStream outputSteam= new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);

    return outputSteam;
    }


}
