package main.commands.application;

import main.Path;
import main.commands.Command;
import main.db.dao.ApplicationDAO;
import main.db.entities.Application;
import main.db.entities.Role;
import main.db.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendApplicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        int roleId = Integer.parseInt(request.getParameter("vacancy"));
        Role role = Role.getRole(roleId);
        Application application = new Application();
        application.setUserId(userId);
        assert role != null;
        application.setRoleId(roleId);
        ApplicationDAO.getInstance().newApplication(application);
        return Path.PAGE__USER;
    }
}
