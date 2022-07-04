package main.commands.order.user;

import main.Path;
import main.commands.Command;
import main.db.dao.OrderDAO;
import main.db.entities.Order;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class OrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((User)session.getAttribute("user")).getId();
        List<Order> orders = OrderDAO.getInstance().getUserOrders(userId);
        request.setAttribute("orders", orders);
        return Path.PAGE__ORDERS;
    }
}
