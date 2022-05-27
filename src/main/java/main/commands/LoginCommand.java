package main.commands;

import main.db.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.Path;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        DBManager dbManager = DBManager.getInstance();

        if (dbManager.check(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.removeAttribute("failedToLogin");
            session.setMaxInactiveInterval(60*60*24); // 1 day
            return Path.PAGE__START_PAGE;
        }
        else {
            HttpSession session = request.getSession();
            session.setAttribute("failedToLogin", true);
            return Path.PAGE__LOGIN;
        }
    }
}
