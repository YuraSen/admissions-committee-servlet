package com.senin.admissions_committee_servlet.web.command;

import com.senin.admissions_committee_servlet.entity.Faculty;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllFacultiesCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Faculty> facultiesList= (List<Faculty>) daoFactory.getFacultyDAO().getAllFacultiesTO();

        request.setAttribute("facultiesList", facultiesList);
        return "/WEB-INF/jsp/applicant/faculties.jsp";
    }
}
