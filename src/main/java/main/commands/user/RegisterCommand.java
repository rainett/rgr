package main.commands.user;

import main.Path;
import main.commands.Command;
import main.db.dao.UserDAO;
import main.db.entities.Role;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("wrongUsername");
        session.removeAttribute("wrongPassword");
        session.removeAttribute("wrongEmail");
        session.removeAttribute("wrongRepeatedPassword");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!validate(request, session)) {
            return Path.PAGE__REGISTRATION;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoleId(Role.CLIENT.ordinal());
        UserDAO.getInstance().addUser(user);
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(60*60*24); // 1 day
        return Path.PAGE__START;
    }

    private boolean validate(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        return validateUsername(username, session) && validateEmail(email, session) && validateRepeatedPassword(password, repeatedPassword, session);
    }

    private boolean validateUsername(String username, HttpSession session) {
        Pattern pattern = Pattern.compile("^\\w+?$");
        Matcher matcher = pattern.matcher(username);
        if (!matcher.find()) {
            session.setAttribute("wrongUsername", "????'?? ?????????????????????? ?????????????? ???????????????????? ?? ???????????????????? ?????????? ???? ????????");
            return false;
        }
        UserDAO userDAO = UserDAO.getInstance();
        if (userDAO.getUser(username) != null) {
            session.setAttribute("wrongUsername", "????'?? ?????????????? ?????????? ????????????????????????");
            return false;
        }
        session.setAttribute("username", username);
        return true;
    }

    private boolean validateEmail(String email, HttpSession session) {
        UserDAO userDAO = UserDAO.getInstance();
        if (userDAO.findUserByEmail(email) != null) {
            session.setAttribute("wrongEmail", "???????????? ?????? ???????????????????????????????? ?????????? ????????????????????????");
            return false;
        }
        return true;
    }

    private boolean validateRepeatedPassword(String password, String repeatedPassword, HttpSession session) {
        if (!password.equals(repeatedPassword)) {
            session.setAttribute("wrongRepeatedPassword", "???????????? ???? ??????????????????????");
            return false;
        }
        return true;
    }
}
