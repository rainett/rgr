package main.commands.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import main.Path;
import main.commands.Command;
import main.db.dao.UserDAO;
import main.db.entities.User;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("wrongUsername");
        session.removeAttribute("wrongPassword");
        session.removeAttribute("wrongEmail");
        session.removeAttribute("wrongRepeatedPassword");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDAO = UserDAO.getInstance();
        User user = userDAO.getUser(username);

        if (user == null) {
            session.setAttribute("wrongUsername", "Неправильне ім'я користувача");
            return Path.PAGE__LOGIN;
        }
        else if (!user.getPassword().equals(password)) {
            session.setAttribute("wrongPassword", "Неправильний пароль користувача");
            return Path.PAGE__LOGIN;
        }
        else {
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(60*60*24); // 1 day
            return Path.PAGE__START;
        }
    }
}
