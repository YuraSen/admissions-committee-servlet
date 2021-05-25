package com.senin.demo.controller.command;

import com.senin.demo.model.DAO.DAOFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {

    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    public String execute(HttpServletRequest request, HttpServletResponse response);

}
