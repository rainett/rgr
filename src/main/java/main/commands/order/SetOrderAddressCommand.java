package main.commands.order;

import main.Path;
import main.commands.Command;
import main.commands.CommandName;
import main.db.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class SetOrderAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("orderA");
        if (order == null) {
            return Path.PAGE__START;
        }
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        order.setAddressId(addressId);
        return controller + CommandName.COMMAND__SHOW_ORDER_PAYMENTS;
    }
}
