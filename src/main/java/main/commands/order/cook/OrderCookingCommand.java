package main.commands.order.cook;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.OrderDAO;
import main.db.entities.Order;
import main.db.entities.OrderState;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class OrderCookingCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = OrderDAO.getInstance().getOrder(orderId);
        order.setStateId(OrderState.COOKING.ordinal());
        order.setWorkerId(userId);
        OrderDAO.getInstance().updateOrder(order);
        return controller + CommandName.COMMAND__SHOW_COOK_ORDERS;
    }

}
