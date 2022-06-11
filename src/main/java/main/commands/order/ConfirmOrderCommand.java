package main.commands.order;

import main.Path;
import main.commands.Command;
import main.db.dao.OrderDAO;
import main.db.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ConfirmOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        new OrderDAO().newOrder(order);
        session.removeAttribute("order");
        return Path.PAGE__START;
    }
}
