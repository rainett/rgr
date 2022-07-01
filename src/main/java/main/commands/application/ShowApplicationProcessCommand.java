package main.commands.application;

import main.Path;
import main.commands.Command;
import main.db.dao.ApplicationDAO;
import main.db.dao.UserDAO;
import main.db.entities.Application;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;

public class ShowApplicationProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        Application application = ApplicationDAO.getInstance().getApplication(applicationId);
        User user = UserDAO.getInstance().getUser(application.getUserId());
        request.setAttribute("application", application);
        request.setAttribute("user", user);
        return Path.PAGE__APPLICATION_PROCESS;
    }
}
