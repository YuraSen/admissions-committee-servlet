package com.senin.demo.controller.command.admin;


import com.senin.demo.controller.command.Command;

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
