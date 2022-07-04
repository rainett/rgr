package main.filters;

import main.commands.CommandName;

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
        commands.add(CommandName.COMMAND__ADDRESSES);
        commands.add(CommandName.COMMAND__NEW_ADDRESS);
        commands.add(CommandName.COMMAND__UPDATE_ADDRESS);
        commands.add(CommandName.COMMAND__DELETE_ADDRESS);
        commands.add(CommandName.COMMAND__SHOW_UPDATE_ADDRESS);
        commands.add(CommandName.COMMAND__SHOW_NEW_ADDRESS);

        commands.add(CommandName.COMMAND__SHOW_EDIT_DISHES);
        commands.add(CommandName.COMMAND__SHOW_UPDATE_DISH);
        commands.add(CommandName.COMMAND__SHOW_NEW_DISH);
        commands.add(CommandName.COMMAND__NEW_DISH);

        commands.add(CommandName.COMMAND__ORDERS);
        commands.add(CommandName.COMMAND__SPECIFIC_ORDER);
        commands.add(CommandName.COMMAND__ORDER_DISHES);
        commands.add(CommandName.COMMAND__CONFIRM_ORDER);
        commands.add(CommandName.COMMAND__CANCEL_ORDER);
        commands.add(CommandName.COMMAND__SET_ORDER_ADDRESS);
        commands.add(CommandName.COMMAND__SET_ORDER_PAYMENT);
        commands.add(CommandName.COMMAND__SHOW_ORDER_DISHES);
        commands.add(CommandName.COMMAND__SHOW_ORDER_ADDRESSES);
        commands.add(CommandName.COMMAND__SHOW_ORDER_PAYMENTS);
        commands.add(CommandName.COMMAND__SHOW_ORDER_CONFIRM);


        commands.add(CommandName.COMMAND__PAYMENTS);
        commands.add(CommandName.COMMAND__NEW_PAYMENT);
        commands.add(CommandName.COMMAND__UPDATE_PAYMENT);
        commands.add(CommandName.COMMAND__DELETE_PAYMENT);
        commands.add(CommandName.COMMAND__SHOW_NEW_PAYMENT);
        commands.add(CommandName.COMMAND__SHOW_UPDATE_PAYMENT);

        commands.add(CommandName.COMMAND__USER);
        commands.add(CommandName.COMMAND__SEND_APPLICATION);
        commands.add(CommandName.COMMAND__SHOW_SEND_APPLICATION);
        commands.add(CommandName.COMMAND__APPLICATION_PROCESS);
        commands.add(CommandName.COMMAND__SHOW_APPLICATION_PROCESS);
        commands.add(CommandName.COMMAND__SHOW_APPLICATIONS);

        commands.add(CommandName.COMMAND__ORDER_COOKED);
        commands.add(CommandName.COMMAND__ORDER_COOKING);
        commands.add(CommandName.COMMAND__SHOW_COOK_ACTIVE_ORDERS);
        commands.add(CommandName.COMMAND__SHOW_COOK_ORDERS);
        commands.add(CommandName.COMMAND__SHOW_SPECIFIC_ORDER_COOK);

        commands.add(CommandName.COMMAND__ORDER_SEND_TO_COOK);
        commands.add(CommandName.COMMAND__SHOW_MANAGER_ORDERS);
        commands.add(CommandName.COMMAND__SPECIFIC_ORDER_MANAGER);
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
            ((HttpServletResponse)response).sendRedirect(controller + CommandName.COMMAND__SHOW_LOGIN);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isAuthorized(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession();
        return session.getAttribute("user") != null;
    }
}
