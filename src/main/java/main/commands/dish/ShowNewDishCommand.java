package main.commands.dish;

import main.Path;
import main.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowNewDishCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return Path.PAGE__NEW_DISH;
    }

}
