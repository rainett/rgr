package main.filters;

import main.Path;
import main.commands.CommandName;
import main.db.dao.AddressDAO;
import main.db.entities.Address;
import main.db.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserAddressFilter implements Filter {

    private static final List<String> commands = new ArrayList<>();

    static {
        commands.add(CommandName.COMMAND__UPDATE_ADDRESS);
        commands.add(CommandName.COMMAND__DELETE_ADDRESS);
        commands.add(CommandName.COMMAND__SHOW_UPDATE_ADDRESS);
        commands.add(CommandName.COMMAND__SET_ORDER_ADDRESS);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("creating address filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("running address filter");
        String command = request.getParameter("command");
        if (commands.contains(command) && !hasAddress(request)) {
            ((HttpServletResponse)response).sendRedirect(Path.PAGE__START);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean hasAddress(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession();
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        int userId = ((User) session.getAttribute("user")).getId();
        Address address = AddressDAO.getInstance().getAddress(addressId);
        return address.getUserId() == userId;
    }
}
