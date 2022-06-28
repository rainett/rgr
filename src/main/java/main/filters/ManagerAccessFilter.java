package main.filters;

import main.Path;
import main.commands.CommandNames;
import main.db.entities.Role;
import main.db.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.Controller.controller;

public class ManagerAccessFilter implements Filter {

    private static final List<String> commands = new ArrayList<>();

    static {
        commands.add(CommandNames.COMMAND__SHOW_EDIT_DISHES);
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
        return (user != null && user.getRole().equals(Role.MANAGER.getRoleStr()));
    }
}
