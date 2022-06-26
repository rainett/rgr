package main.commands.user;

import main.Path;
import main.commands.Command;
import main.commands.CommandNames;
import main.db.dao.UserDAO;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.Controller.controller;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
        return validateUsername(username, session) && validateEmail(email, session) && validatePassword(password, session) && validateRepeatedPassword(password, repeatedPassword, session);
    }

    private boolean validateUsername(String username, HttpSession session) {
        Pattern pattern = Pattern.compile("^\\w+?$");
        Matcher matcher = pattern.matcher(username);
        if (!matcher.find()) {
            session.setAttribute("wrongUsername", "Ім'я користувача повинне складатись з латинських літер та цифр");
            return false;
        }
        UserDAO userDAO = UserDAO.getInstance();
        if (userDAO.getUser(username) != null) {
            session.setAttribute("wrongUsername", "Ім'я зайняте іншим користувачем");
            return false;
        }
        session.setAttribute("username", username);
        return true;
    }

    private boolean validateEmail(String email, HttpSession session) {
        UserDAO userDAO = UserDAO.getInstance();
        if (userDAO.findUserByEmail(email) != null) {
            session.setAttribute("wrongEmail", "Адреса вже використовується іншим користувачем");
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password, HttpSession session) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            session.setAttribute("wrongPassword", "Пароль має бути більшим за 7 симв. та  містити хоч одну цифру та літеру");
            return false;
        }
        return true;
    }

    private boolean validateRepeatedPassword(String password, String repeatedPassword, HttpSession session) {
        if (!password.equals(repeatedPassword)) {
            session.setAttribute("wrongRepeatedPassword", "Паролі не співпадають");
            return false;
        }
        return true;
    }
}
