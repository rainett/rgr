package main.commands.dish;

import main.Path;
import main.commands.Command;
import main.commands.order.user.ShowOrderDishesCommand;

import javax.servlet.http.HttpServletRequest;

public class DishesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ShowOrderDishesCommand.ShowDishes(request);
        return Path.PAGE__DISHES;
    }

}
