package main.filters;

import main.Path;
import main.commands.CommandName;
import main.db.entities.Role;
import main.db.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourierAccessFilter implements Filter {

    private static final List<String> commands = new ArrayList<>();

    static {
        commands.add(CommandName.COMMAND__ORDER_DELIVERED);
        commands.add(CommandName.COMMAND__ORDER_DELIVERING);
        commands.add(CommandName.COMMAND__SHOW_DELIVERY_ACTIVE_ORDERS);
        commands.add(CommandName.COMMAND__SHOW_DELIVERY_ORDERS);
        commands.add(CommandName.COMMAND__SHOW_SPECIFIC_ORDER_DELIVERY);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("creating courier access filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("running courier access filter");
        String command = request.getParameter("command");
        if (commands.contains(command) && !isCook(request)) {
            ((HttpServletResponse)response).sendRedirect(Path.PAGE__START);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isCook(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");
        return (user.getRoleId() == Role.COURIER.ordinal());
    }
}
