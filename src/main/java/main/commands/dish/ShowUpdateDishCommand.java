package main.commands.dish;

import main.Path;
import main.commands.Command;
import main.db.dao.DishDAO;
import main.db.entities.Dish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUpdateDishCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int dishId = Integer.parseInt(request.getParameter("dishId"));
        Dish dish = DishDAO.getInstance().getDish(dishId);
        request.setAttribute("dish", dish);
        return Path.PAGE__UPDATE_DISH;
    }

}
