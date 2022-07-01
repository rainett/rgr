package main.commands.dish;

import main.Path;
import main.commands.Command;
import main.commands.order.ShowOrderDishesCommand;

import javax.servlet.http.HttpServletRequest;

public class ShowEditDishesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ShowOrderDishesCommand.ShowDishes(request);
        return Path.PAGE__EDIT_DISHES;
    }

}
