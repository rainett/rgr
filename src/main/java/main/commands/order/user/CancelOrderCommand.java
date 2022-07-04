package main.commands.order.user;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.OrderDAO;
import main.db.entities.Order;
import main.db.entities.OrderState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class CancelOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("orderA");
        session.removeAttribute("orderedDishesA");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = OrderDAO.getInstance().getOrder(orderId);
        if (order != null && (OrderState.getState(order.getStateId()) == OrderState.RESERVED || OrderState.getState(order.getStateId()) == OrderState.FINISHED)) {
            OrderDAO.getInstance().deleteOrder(orderId);
        }
        return controller + CommandName.COMMAND__ORDERS;
    }
}
