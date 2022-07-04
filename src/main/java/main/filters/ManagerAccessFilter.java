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

public class ManagerAccessFilter implements Filter {

    private static final List<String> commands = new ArrayList<>();

    static {
        commands.add(CommandName.COMMAND__SHOW_EDIT_DISHES);
        commands.add(CommandName.COMMAND__SHOW_UPDATE_DISH);
        commands.add(CommandName.COMMAND__SHOW_NEW_DISH);
        commands.add(CommandName.COMMAND__NEW_DISH);
        commands.add(CommandName.COMMAND__UPDATE_DISH);
        commands.add(CommandName.COMMAND__SHOW_APPLICATIONS);
        commands.add(CommandName.COMMAND__SHOW_APPLICATION_PROCESS);
        commands.add(CommandName.COMMAND__APPLICATION_PROCESS);
        commands.add(CommandName.COMMAND__SHOW_MANAGER_ORDERS);
        commands.add(CommandName.COMMAND__SPECIFIC_ORDER_MANAGER);
        commands.add(CommandName.COMMAND__ORDER_SEND_TO_COOK);
        commands.add(CommandName.COMMAND__ORDER_SEND_TO_DELIVERY);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("creating manager access filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("running manager access filter");
        String command = request.getParameter("command");
        if (commands.contains(command) && !isManager(request)) {
            ((HttpServletResponse)response).sendRedirect(Path.PAGE__START);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isManager(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");
        return (Role.getRole(user.getRoleId()) == Role.MANAGER);
    }
}
