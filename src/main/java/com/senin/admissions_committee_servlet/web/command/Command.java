package com.senin.admissions_committee_servlet.web.command;

import com.senin.admissions_committee_servlet.DAO.DAOFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {

    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    public String execute(HttpServletRequest request, HttpServletResponse response);

}
