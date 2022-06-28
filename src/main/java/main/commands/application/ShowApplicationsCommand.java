package main.commands.application;

import main.Path;
import main.commands.Command;
import main.db.dao.ApplicationDAO;
import main.db.entities.Application;
import main.db.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowApplicationsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Application> applications = ApplicationDAO.getInstance().getAllApplications();
        request.setAttribute("applications", applications);
        return Path.PAGE__APPLICATIONS;
    }
}
