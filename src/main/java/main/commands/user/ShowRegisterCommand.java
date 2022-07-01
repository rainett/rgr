package main.commands.user;

import main.Path;
import main.commands.Command;
import main.commands.CommandName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;

public class ShowRegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return controller + CommandName.COMMAND__USER;
        }

        session.removeAttribute("wrongUsername");
        session.removeAttribute("wrongPassword");
        session.removeAttribute("wrongEmail");
        session.removeAttribute("wrongRepeatedPassword");

        return Path.PAGE__REGISTRATION;
    }
}
