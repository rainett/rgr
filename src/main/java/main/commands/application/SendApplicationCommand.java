package main.commands.application;

import main.Path;
import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.ApplicationDAO;
import main.db.entities.Application;
import main.db.entities.Role;
import main.db.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.Controller.controller;

public class SendApplicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        Role role = Role.getRole(request.getParameter("vacancy"));
        Application application = new Application();
        application.setUserId(userId);
        assert role != null;
        application.setRole(role.getRoleStr());
        ApplicationDAO.getInstance().newApplication(application);
        return Path.PAGE__USER;
    }
}
