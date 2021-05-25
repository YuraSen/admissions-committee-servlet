package com.senin.demo.model.DAO.mysql;

public final class Constants {
    public Constants() {
    }

    public static final String SQL_FIND_ALL_APPLICANTS = "SELECT * FROM applicant";
    public static final String SQL_FIND_ALL_ADMISSION_REQUESTS = "SELECT * FROM admission_request";
    public static final String SQL_FIND_APPLICANT_BY_USERNAME = "SELECT * FROM  applicant WHERE username = ?";
}
