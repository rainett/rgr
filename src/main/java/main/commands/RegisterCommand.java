package main.commands;

import main.Path;
import main.db.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        if (userDAO.findUser(username, email) != null) {
            HttpSession session = request.getSession();
            session.setAttribute("failedToRegister", true);
            return Path.PAGE__REGISTRATION;
        }
        else {
            new UserDAO().addUser(username, password, email);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.removeAttribute("failedToRegister");
            session.setMaxInactiveInterval(60*60*24); // 1 day
            return Path.PAGE__START_PAGE;
        }
    }
}
