package main.commands.dish;

import main.Path;
import main.commands.Command;
import main.commands.order.ShowOrderDishesCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowNewDishCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.PAGE__NEW_DISH;
    }

}
