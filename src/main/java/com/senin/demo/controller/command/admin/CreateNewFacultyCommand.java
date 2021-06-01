package com.senin.demo.controller.command.admin;

import com.senin.demo.controller.command.Command;
import com.senin.demo.exception.DbProcessingException;
import com.senin.demo.model.entity.Faculty;
import com.senin.demo.service.FacultyService;
import com.senin.demo.utils.util.FacultyDTOMapper;
import com.senin.demo.utils.validation.FacultyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateNewFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(CreateNewFacultyCommand.class);
    FacultyService facultyService;

    public CreateNewFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        String lang = (String) request.getSession().getAttribute("lang");

        Map<String, String> facultyParameters = request.getParameterMap().entrySet().stream()
                .filter(entry -> !("command".equals(entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, stringEntry -> request.getParameter(stringEntry.getKey())));


        FacultyValidator facultyValidator = new FacultyValidator(lang);
        Map<String, String> errors = facultyValidator.validateFaculty(facultyParameters);
        if (!errors.isEmpty()) {
            facultyParameters.entrySet().stream().forEach(c -> request.setAttribute(c.getKey(), c.getValue()));
            errors.entrySet().stream().forEach(entity -> request.setAttribute(entity.getKey(), entity.getValue()));
            return "WEB-INF/jsp/admin/adminCreateFacultyFrom.jsp";
        }
        FacultyDTOMapper facultyDTOMapper = new FacultyDTOMapper();
        Faculty faculty = facultyDTOMapper.getFaculty(facultyParameters);

        try {
            facultyService.create(faculty);
            LOG.info("Faculty created");
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while creating new faculty : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }


        try {
            response.sendRedirect("/controller?command=adminWorkspace");
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        return "";
    }
}
