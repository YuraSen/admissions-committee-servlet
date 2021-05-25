package com.senin.demo.controller.command;

import com.senin.demo.model.DAO.DAOFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@FunctionalInterface
public interface Command {

    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException;

}
