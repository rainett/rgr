package main.commands.application;

import main.Path;
import main.commands.Command;
import main.db.dao.ApplicationDAO;
import main.db.dao.UserDAO;
import main.db.entities.Application;
import main.db.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowApplicationProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        Application application = ApplicationDAO.getInstance().getApplication(applicationId);
        User user = UserDAO.getInstance().getUser(application.getUserId());
        request.setAttribute("application", application);
        request.setAttribute("user", user);
        return Path.PAGE__APPLICATION_PROCESS;
    }
}
