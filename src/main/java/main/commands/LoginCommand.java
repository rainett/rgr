package main.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.Path;
import main.db.dao.UserDAO;
import main.db.entities.User;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUser(username);

        if (user == null) {
            HttpSession session = request.getSession();
            session.setAttribute("wrongUsername", true);
            return Path.PAGE__LOGIN;
        }
        else if (!user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("wrongPassword", true);
            return Path.PAGE__LOGIN;
        }
        else {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.removeAttribute("wrongUsername");
            session.removeAttribute("wrongPassword");
            session.setMaxInactiveInterval(60*60*24); // 1 day
            return Path.PAGE__START;
        }
    }
}
