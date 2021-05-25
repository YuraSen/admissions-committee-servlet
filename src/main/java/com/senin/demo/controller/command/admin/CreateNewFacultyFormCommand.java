package com.senin.demo.controller.command.admin;


import com.senin.demo.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateNewFacultyFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "WEB-INF\\jsp\\admin\\adminCreateEditFacultyFrom.jsp";
    }
}
