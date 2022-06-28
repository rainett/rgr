package main.filters;

import main.Path;
import main.commands.CommandName;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;
import main.db.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserPaymentFilter implements Filter {

    private static final List<String> commands = new ArrayList<>();

    static {
        commands.add(CommandName.COMMAND__UPDATE_PAYMENT);
        commands.add(CommandName.COMMAND__DELETE_PAYMENT);
        commands.add(CommandName.COMMAND__SHOW_UPDATE_PAYMENT);
        commands.add(CommandName.COMMAND__SET_ORDER_PAYMENT);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("creating payment filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("running payment filter");
        String command = request.getParameter("command");
        if (commands.contains(command) && !hasPayment(request)) {
            ((HttpServletResponse)response).sendRedirect(Path.PAGE__START);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean hasPayment(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession();
        int paymentId = Integer.parseInt(request.getParameter("paymentId"));
        int userId = ((User) session.getAttribute("user")).getId();
        Payment payment = PaymentDAO.getInstance().getPayment(paymentId);
        return payment.getUserId() == userId;
    }
}
