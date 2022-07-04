package main.commands.order.delivery;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.OrderDAO;
import main.db.entities.Order;
import main.db.entities.OrderState;

import javax.servlet.http.HttpServletRequest;

import static main.Controller.controller;


public class OrderDeliveredCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = OrderDAO.getInstance().getOrder(orderId);
        order.setStateId(OrderState.FINISHED.ordinal());
        order.setWorkerId(0);
        OrderDAO.getInstance().updateOrder(order);
        return controller + CommandName.COMMAND__SHOW_DELIVERY_ACTIVE_ORDERS;
    }

}
