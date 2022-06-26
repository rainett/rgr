package main.commands.user;

import main.Path;
import main.commands.Command;
import main.commands.CommandNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.Controller.controller;

public class UserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return controller + CommandNames.COMMAND__SHOW_LOGIN;
        }
        return Path.PAGE__USER;
    }
}
