package main.commands.order.user;

import main.commands.Command;
import main.commands.CommandName;
import main.db.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class SetOrderPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("orderA");
        int paymentId = Integer.parseInt(request.getParameter("paymentId"));
        order.setPaymentId(paymentId);
        return controller + CommandName.COMMAND__SHOW_ORDER_CONFIRM;
    }
}
