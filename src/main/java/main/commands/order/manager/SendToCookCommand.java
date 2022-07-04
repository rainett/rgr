package main.commands.order.manager;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.*;
import main.db.entities.*;

import javax.servlet.http.HttpServletRequest;

import static main.Controller.controller;


public class SendToCookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = OrderDAO.getInstance().getOrder(orderId);
        order.setStateId(OrderState.PRE_COOKING.ordinal());
        OrderDAO.getInstance().updateOrder(order);
        return controller + CommandName.COMMAND__SHOW_MANAGER_ORDERS;
    }

}
