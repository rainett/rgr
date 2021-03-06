package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowUpdatePaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int paymentId = Integer.parseInt(request.getParameter("paymentId"));
        Payment payment = PaymentDAO.getInstance().getPayment(paymentId);
        request.setAttribute("payment", payment);
        session.removeAttribute("wrongNumber");
        return Path.PAGE__UPDATE_PAYMENT;
    }
}
