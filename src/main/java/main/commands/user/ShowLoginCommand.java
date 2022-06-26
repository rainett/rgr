package main.commands.user;

import main.Path;
import main.commands.Command;
import main.commands.CommandNames;
import main.db.dao.UserDAO;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static main.Controller.controller;

public class ShowLoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return controller + CommandNames.COMMAND__USER;
        }

        session.removeAttribute("wrongUsername");
        session.removeAttribute("wrongPassword");
        session.removeAttribute("wrongEmail");
        session.removeAttribute("wrongRepeatedPassword");

        return Path.PAGE__LOGIN;
    }
}
