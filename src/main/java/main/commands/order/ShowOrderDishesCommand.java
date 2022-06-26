package main.commands.order;

import main.Path;
import main.commands.Command;
import main.db.dao.DishesDAO;
import main.db.entities.Dish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOrderDishesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sorting = request.getParameter("sorting");
        List<Dish> dishes = DishesDAO.getInstance().getAllDishes(sorting);
        request.setAttribute("dishes", dishes);
        return Path.PAGE__ORDER_DISHES;
    }

}
