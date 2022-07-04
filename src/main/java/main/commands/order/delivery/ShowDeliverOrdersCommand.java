package main.commands.order.delivery;

import main.Path;
import main.commands.Command;
import main.db.dao.OrderDAO;
import main.db.entities.Order;
import main.db.entities.OrderState;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ShowDeliverOrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Order> orders = OrderDAO.getInstance().getAllOrders(OrderState.PRE_DELIVERING.ordinal());
        request.setAttribute("orders", orders);
        return Path.PAGE__DELIVER_ORDERS;
    }
}
