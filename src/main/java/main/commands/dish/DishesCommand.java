package main.commands.dish;

import main.Path;
import main.commands.Command;
import main.db.dao.DishesDAO;
import main.db.entities.Dish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DishesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sorting = request.getParameter("sorting");
        List<Dish> dishes = DishesDAO.getInstance().getAllDishes(sorting);
        request.setAttribute("dishes", dishes);
        return Path.PAGE__DISHES;
    }

}
