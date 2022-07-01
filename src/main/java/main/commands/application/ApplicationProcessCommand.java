package main.commands.application;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.ApplicationDAO;
import main.db.entities.Application;

import javax.servlet.http.HttpServletRequest;

import static main.Controller.controller;

public class ApplicationProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
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
