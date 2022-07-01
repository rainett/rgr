package main.commands.user;

import main.Path;
import main.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class UnknownCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return Path.PAGE__START;
    }
}
