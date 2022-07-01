package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PaymentsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = ((User) session.getAttribute("user"));
        List<Payment> payments = PaymentDAO.getInstance().getUserPayments(user.getId());
        request.setAttribute("payments", payments);
        return Path.PAGE__PAYMENTS;
    }
}
