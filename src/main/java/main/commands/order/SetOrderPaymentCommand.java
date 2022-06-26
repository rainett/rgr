package main.commands.order;

import main.Path;
import main.commands.Command;
import main.commands.CommandNames;
import main.db.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class SetOrderPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("orderA");
        int paymentId = Integer.parseInt(request.getParameter("paymentId"));
        order.setPaymentId(paymentId);
        return controller + CommandNames.COMMAND__SHOW_ORDER_CONFIRM;
    }
}
