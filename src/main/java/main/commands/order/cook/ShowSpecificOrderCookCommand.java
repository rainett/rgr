package main.commands.order.cook;

import main.Path;
import main.commands.Command;
import main.db.dao.*;
import main.db.entities.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class ShowSpecificOrderCookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = OrderDAO.getInstance().getOrder(orderId);
        List<OrderedDish> orderedDishes = OrderedDishesDAO.getInstance().getOrderedDishes(orderId);
        List<Dish> dishes = new ArrayList<>();
        orderedDishes.forEach(o -> dishes.add(DishDAO.getInstance().getDish(o.getDishId())));
        List<Photo> photos = new ArrayList<>();
        dishes.forEach(d -> photos.add(PhotoDAO.getInstance().getPhoto(d.getPhotoId())));
        request.setAttribute("order", order);
        request.setAttribute("orderedDishes", orderedDishes);
        request.setAttribute("dishes", dishes);
        request.setAttribute("photos", photos);
        return Path.PAGE__SPECIFIC_ORDER_COOK;
    }

}
