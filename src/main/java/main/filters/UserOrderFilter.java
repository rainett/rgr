package main.filters;

import main.Path;
import main.commands.CommandName;
import main.db.dao.OrderDAO;
import main.db.entities.Order;
import main.db.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserOrderFilter implements Filter {

    private static final List<String> commands = new ArrayList<>();

    static {
        commands.add(CommandName.COMMAND__SPECIFIC_ORDER);
        commands.add(CommandName.COMMAND__CANCEL_ORDER);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("creating order filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("running order filter");
        String command = request.getParameter("command");
        if (commands.contains(command) && !hasPayment(request)) {
            ((HttpServletResponse)response).sendRedirect(Path.PAGE__START);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean hasPayment(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = OrderDAO.getInstance().getOrder(orderId);
        int userId = ((User)session.getAttribute("user")).getId();
        return order.getUserId() == userId;
    }
}
