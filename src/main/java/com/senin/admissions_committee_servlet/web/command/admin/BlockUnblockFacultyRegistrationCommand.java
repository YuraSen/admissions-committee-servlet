package com.senin.admissions_committee_servlet.web.command.admin;


import com.senin.admissions_committee_servlet.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUnblockFacultyRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        daoFactory.getFacultyDAO().changeAdmissionOpenStatus(action, facultyId);
        return "/controller?command=adminWorkspace";
    }
}
