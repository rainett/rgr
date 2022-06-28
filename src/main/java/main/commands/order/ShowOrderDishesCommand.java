package main.commands.order;

import main.Path;
import main.commands.Command;
import main.db.dao.DishDAO;
import main.db.dao.PhotoDAO;
import main.db.entities.Dish;
import main.db.entities.Photo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowOrderDishesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ShowDishes(request);
        return Path.PAGE__ORDER_DISHES;
    }

    public static void ShowDishes(HttpServletRequest request) {
        String sorting = request.getParameter("sorting");
        List<Dish> dishes = DishDAO.getInstance().getAllDishes(sorting);
        List<Photo> photos = new ArrayList<>();
        dishes.forEach(d -> photos.add(PhotoDAO.getInstance().getPhoto(d.getId())));
        request.setAttribute("dishes", dishes);
        request.setAttribute("photos", photos);
    }

}
