package main.commands.order;

import main.Path;
import main.commands.Command;
import main.db.dao.*;
import main.db.entities.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpecificOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = OrderDAO.getInstance().getOrder(orderId);

        Address address = AddressDAO.getInstance().getAddress(order.getAddressId());
        Payment payment = PaymentDAO.getInstance().getPayment(order.getPaymentId());
        List<OrderedDish> orderedDishes = OrderedDishesDAO.getInstance().getOrderedDishes(orderId);
        List<Dish> dishes = new ArrayList<>();
        for (OrderedDish orderedDish : orderedDishes) {
            dishes.add(DishesDAO.getInstance().findDish(orderedDish.getDishId()));
        }
        request.setAttribute("order", order);
        request.setAttribute("orderedDishes", orderedDishes);
        request.setAttribute("dishes", dishes);
        request.setAttribute("address", address);
        request.setAttribute("payment", payment);
        return Path.PAGE__SPECIFIC_ORDER;
    }
}
