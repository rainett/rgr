package main.commands.order;

import main.Path;
import main.commands.Command;
import main.db.dao.OrderDAO;
import main.db.dao.OrderedDishesDAO;
import main.db.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CancelOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            OrderDAO orderDAO = new OrderDAO();
            new OrderedDishesDAO().deleteOrderedDishes(orderDAO.getOrder(orderId).getOrderedId());
            orderDAO.deleteOrder(orderId);
        } else {
            new OrderedDishesDAO().deleteOrderedDishes(order.getOrderedId());
        }
        session.removeAttribute("order");
        return Path.PAGE__START;
    }
}
