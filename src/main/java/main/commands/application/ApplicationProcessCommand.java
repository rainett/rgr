package main.commands.application;

import main.Path;
import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.ApplicationDAO;
import main.db.dao.UserDAO;
import main.db.entities.Application;
import main.db.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.Controller.controller;

public class ApplicationProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String resp = request.getParameter("response");
        if (resp.equals("approve")) {
            Application application = ApplicationDAO.getInstance().getApplication(applicationId);
            application.setState(true);
            ApplicationDAO.getInstance().updateApplication(application);
        } else {
            ApplicationDAO.getInstance().deleteApplication(applicationId);
        }
        return controller + CommandName.COMMAND__SHOW_APPLICATIONS;
    }
}
