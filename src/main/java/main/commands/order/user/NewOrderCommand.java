package main.commands.order.user;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.DishDAO;
import main.db.entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static main.Controller.controller;


public class NewOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String[] dishIds = request.getParameterValues("dish_id");
        String[] dishAmounts = request.getParameterValues("dish_amount");

        if (dishIds == null || dishAmounts == null) {
            return controller + CommandName.COMMAND__SHOW_ORDER_DISHES;
        }

        List<OrderedDish> orderedDishes = new ArrayList<>();
        int price = 0;
        for (int i = 0; i < dishIds.length; i++) {
            if (dishAmounts[i].equals("0")) {
                continue;
            }
            int dishAmount = Integer.parseInt(dishAmounts[i]);
            Dish dish = DishDAO.getInstance().getDish(Integer.parseInt(dishIds[i]));
            price += dish.getPrice() * dishAmount;
            OrderedDish orderedDish = new OrderedDish();
            orderedDish.setDishId(dish.getId());
            orderedDish.setDishAmount(dishAmount);
            orderedDishes.add(orderedDish);
        }

        if (orderedDishes.isEmpty()) {
            return controller + CommandName.COMMAND__SHOW_ORDER_DISHES;
        }

        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        Order order = new Order();
        order.setUserId(userId);
        order.setPrice(price);
        order.setStateId(OrderState.RESERVED.ordinal());
        session.setAttribute("orderedDishesA", orderedDishes);
        session.setAttribute("orderA", order);
        return controller + CommandName.COMMAND__SHOW_ORDER_ADDRESSES;
    }
}
