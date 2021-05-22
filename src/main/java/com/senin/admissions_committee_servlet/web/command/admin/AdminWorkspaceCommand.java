package com.senin.admissions_committee_servlet.web.command.admin;

import com.senin.admissions_committee_servlet.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminWorkspaceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/jsp/admin/admin_workspace.jsp";
    }
}