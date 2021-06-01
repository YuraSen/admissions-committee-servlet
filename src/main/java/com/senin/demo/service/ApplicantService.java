package com.senin.demo.service;

import com.senin.demo.exception.ApplicantNotFoundException;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.DAO.ApplicantDAO;
import com.senin.demo.model.DAO.DAOFactory;
import com.senin.demo.model.DAO.mysql.ConnectionPoolHolder;
import com.senin.demo.model.entity.Applicant;
import com.senin.demo.model.entity.ApplicantProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

public class ApplicantService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);


    public List<Applicant> findAll() {
        try (ApplicantDAO dao = daoFactory.getApplicantDAO()){
            return dao.findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void delete(Long applicantId) {
        try (ApplicantDAO dao = daoFactory.getApplicantDAO()){
            dao.delete(applicantId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void update(String role, String applicantStatus, Long applicantId)  {
        try (ApplicantDAO dao = daoFactory.getApplicantDAO()){
            dao.updateApplicant(role, applicantStatus, applicantId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public Applicant findById(Long applicantId) {
        try (ApplicantDAO dao = daoFactory.getApplicantDAO()){
         return  dao.findApplicantById(applicantId)
                 .orElseThrow(() -> new ApplicantNotFoundException("Can not find applicant with id: " + applicantId));
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }
    public Optional<ApplicantProfile> getApplicantProfile(Applicant applicant) {
        try (ApplicantDAO dao = daoFactory.getApplicantDAO()) {
            return dao.getApplicantProfile(applicant);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void updateApplicantProfile(ApplicantProfile applicantProfile) {
        try (ApplicantDAO dao = daoFactory.getApplicantDAO()) {
            dao.updateApplicantProfile(applicantProfile);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not update applicant profile with id:" + applicantProfile.getId());
        }
    }

    public Optional<Applicant> findApplicantByUsername(String username) {
        try (ApplicantDAO dao = daoFactory.getApplicantDAO()) {
            return dao.findApplicantByUsername(username);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void create(Applicant applicant, ApplicantProfile applicantProfile) {
        try (ApplicantDAO dao = daoFactory.getApplicantDAO()) {
            dao.insertApplicant(applicant, applicantProfile);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not create applicant!");
        }
    }


    public String saveFile(HttpServletRequest request) throws IOException {


        Part filePart = null;
        try {
            filePart = request.getPart("file");
            if (!(filePart.getSize() > 0)) {
                return "";
            }

        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        try (InputStream in = ConnectionPoolHolder.class.getClassLoader().getResourceAsStream("db\\db.properties")) {
            properties.load(in);
        }

        String uuidFile = UUID.randomUUID().toString();
        String fileName = uuidFile + "." + getFileName(filePart);

        try (OutputStream out = new FileOutputStream(new File(properties.getProperty("path") + File.separator + fileName));
             InputStream filecontent = filePart.getInputStream();
        ) {
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileName;
    }



    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public Optional<ApplicantProfile> getApplicantProfileById(Long applicantProfileId) {
        try (ApplicantDAO dao = daoFactory.getApplicantDAO()) {
            return dao.getApplicantProfileById(applicantProfileId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }
}
