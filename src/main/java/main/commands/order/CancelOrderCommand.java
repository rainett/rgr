package main.commands.order;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.OrderDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class CancelOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("orderA");
        session.removeAttribute("orderedDishesA");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDAO.getInstance().deleteOrder(orderId);
        return controller + CommandName.COMMAND__ORDERS;
    }
}
