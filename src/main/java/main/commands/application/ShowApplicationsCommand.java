package main.commands.application;

import main.Path;
import main.commands.Command;
import main.db.dao.ApplicationDAO;
import main.db.entities.Application;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowApplicationsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Application> applications = ApplicationDAO.getInstance().getAllApplications();
        request.setAttribute("applications", applications);
        return Path.PAGE__APPLICATIONS;
    }
}
