package main.commands.dish;

import main.Path;
import main.commands.Command;
import main.commands.order.ShowOrderDishesCommand;
import main.db.dao.DishesDAO;
import main.db.dao.PhotoDAO;
import main.db.entities.Dish;
import main.db.entities.Photo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class DishesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ShowOrderDishesCommand.ShowDishes(request);
        return Path.PAGE__DISHES;
    }

}
