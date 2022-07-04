package main.commands.order.user;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.OrderDAO;
import main.db.dao.OrderedDishesDAO;
import main.db.entities.Order;
import main.db.entities.OrderedDish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static main.Controller.controller;


public class ConfirmOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("orderA");
        List<OrderedDish> orderedDishes = (List<OrderedDish>) session.getAttribute("orderedDishesA");
        int orderId = OrderDAO.getInstance().newOrder(order);
        orderedDishes.forEach(oD -> oD.setOrderId(orderId));
        OrderedDishesDAO.getInstance().newOrderedDishes(orderedDishes);
        session.removeAttribute("orderA");
        session.removeAttribute("orderedDishesA");
        return controller + CommandName.COMMAND__ORDERS;
    }
}
