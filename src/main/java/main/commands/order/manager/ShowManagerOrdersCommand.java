package main.commands.order.manager;

import main.Path;
import main.commands.Command;
import main.db.dao.OrderDAO;
import main.db.entities.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ShowManagerOrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Order> orders = OrderDAO.getInstance().getAllOrders();
        request.setAttribute("orders", orders);
        return Path.PAGE__MANAGER_ORDERS;
    }
}
