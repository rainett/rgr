package main.filters;

import main.commands.CommandNames;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.Controller.controller;

public class UserAccessFilter implements Filter {

    private static final List<String> commands = new ArrayList<>();

    static {
        commands.add(CommandNames.COMMAND__ADDRESSES);
        commands.add(CommandNames.COMMAND__NEW_ADDRESS);
        commands.add(CommandNames.COMMAND__UPDATE_ADDRESS);
        commands.add(CommandNames.COMMAND__DELETE_ADDRESS);
        commands.add(CommandNames.COMMAND__SHOW_UPDATE_ADDRESS);
        commands.add(CommandNames.COMMAND__SHOW_NEW_ADDRESS);

        commands.add(CommandNames.COMMAND__ORDERS);
        commands.add(CommandNames.COMMAND__SPECIFIC_ORDER);
        commands.add(CommandNames.COMMAND__ORDER_DISHES);
        commands.add(CommandNames.COMMAND__CONFIRM_ORDER);
        commands.add(CommandNames.COMMAND__CANCEL_ORDER);
        commands.add(CommandNames.COMMAND__SET_ORDER_ADDRESS);
        commands.add(CommandNames.COMMAND__SET_ORDER_PAYMENT);
        commands.add(CommandNames.COMMAND__SHOW_ORDER_DISHES);
        commands.add(CommandNames.COMMAND__SHOW_ORDER_ADDRESSES);
        commands.add(CommandNames.COMMAND__SHOW_ORDER_PAYMENTS);
        commands.add(CommandNames.COMMAND__SHOW_ORDER_CONFIRM);


        commands.add(CommandNames.COMMAND__PAYMENTS);
        commands.add(CommandNames.COMMAND__NEW_PAYMENT);
        commands.add(CommandNames.COMMAND__UPDATE_PAYMENT);
        commands.add(CommandNames.COMMAND__DELETE_PAYMENT);
        commands.add(CommandNames.COMMAND__SHOW_NEW_PAYMENT);
        commands.add(CommandNames.COMMAND__SHOW_UPDATE_PAYMENT);

        commands.add(CommandNames.COMMAND__USER);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("creating access filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("running access filter");
        String command = request.getParameter("command");
        if (commands.contains(command) && !isAuthorized(request)) {
            ((HttpServletResponse)response).sendRedirect(controller + CommandNames.COMMAND__SHOW_LOGIN);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isAuthorized(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession();
        return session.getAttribute("user") != null;
    }
}
