package main.commands.order;

import main.Path;
import main.commands.Command;
import main.db.dao.OrderedDishesDAO;
import main.db.dao.PaymentDAO;
import main.db.dao.UserDAO;
import main.db.entities.Order;
import main.db.entities.OrderedDish;
import main.db.entities.Payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OrderDishesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String[] dishIds = request.getParameterValues("dish_id");
        String[] dishAmounts = request.getParameterValues("dish_amount");
        List<OrderedDish> orderedDishes = new ArrayList<>();
        for (int i = 0; i < dishIds.length; i++) {
            if (dishAmounts[i].equals("0")) {
                continue;
            }
            OrderedDish orderedDish = new OrderedDish();
            orderedDish.setOrderedId(0);
            orderedDish.setDishId(Integer.parseInt(dishIds[i]));
            orderedDish.setDishAmount(Integer.parseInt(dishAmounts[i]));
            orderedDishes.add(orderedDish);
        }
        HttpSession session = request.getSession();
        Order order = new Order();
        order.setClientId(new UserDAO().findUser((String) session.getAttribute("username")).getClientId());
        int[] idAndPrice = new OrderedDishesDAO().newOrderedDishes(orderedDishes);
        order.setOrderedId(idAndPrice[0]);
        order.setPrice(idAndPrice[1]);
        session.setAttribute("order", order);
        return Path.PAGE__ORDER_ADDRESS;
    }
}
