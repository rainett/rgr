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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.Controller.controller;

public class ShowSendApplicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        Application application = ApplicationDAO.getInstance().getUserApplication(userId);
        if (application != null) {
            if (!application.getState()) {
                return Path.PAGE__APPLICATION;
            } else {
                User user = UserDAO.getInstance().getUser(userId);
                user.setRoleId(application.getRoleId());
                UserDAO.getInstance().updateUser(user);
                ApplicationDAO.getInstance().deleteApplication(application.getId());
                return controller + CommandName.COMMAND__LOGOUT;
            }
        }
        return Path.PAGE__SEND_APPLICATION;
    }
}
