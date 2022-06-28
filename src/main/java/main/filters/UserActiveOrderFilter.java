package main.filters;

import main.Path;
import main.commands.CommandName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserActiveOrderFilter implements Filter {

    private static final List<String> commands = new ArrayList<>();

    static {
        commands.add(CommandName.COMMAND__SET_ORDER_ADDRESS);
        commands.add(CommandName.COMMAND__SET_ORDER_PAYMENT);
        commands.add(CommandName.COMMAND__SHOW_ORDER_ADDRESSES);
        commands.add(CommandName.COMMAND__SHOW_ORDER_PAYMENTS);
        commands.add(CommandName.COMMAND__SHOW_ORDER_CONFIRM);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("creating active order filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("running active order filter");
        String command = request.getParameter("command");
        if (commands.contains(command) && !hasActiveOrder(request)) {
            ((HttpServletResponse)response).sendRedirect(Path.PAGE__START);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean hasActiveOrder(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession();
        return session.getAttribute("orderA") != null;
    }
}
