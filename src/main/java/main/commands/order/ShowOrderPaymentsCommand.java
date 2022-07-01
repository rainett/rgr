package main.commands.order;

import main.Path;
import main.commands.Command;
import main.db.dao.PaymentDAO;
import main.db.entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ShowOrderPaymentsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        List<Payment> payments = PaymentDAO.getInstance().getUserPayments(userId);
        request.setAttribute("payments", payments);
        return Path.PAGE__ORDER_PAYMENTS;
    }
}
