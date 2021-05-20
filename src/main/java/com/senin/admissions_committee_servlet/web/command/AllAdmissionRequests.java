package com.senin.admissions_committee_servlet.web.command;

import com.senin.admissions_committee_servlet.entity.AdmissionRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllAdmissionRequests  implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<AdmissionRequest> admissionRequests = (List<AdmissionRequest>) daoFactory.getAdmissionRequestDAO().selectAdmissionRequestsTO();

        request.setAttribute("admissionRequests", admissionRequests);

        return "/WEB-INF/jsp/admissionRequests.jsp";
    }
}
