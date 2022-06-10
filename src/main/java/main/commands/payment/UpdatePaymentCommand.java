package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdatePaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PaymentDAO paymentDAO = new PaymentDAO();
        String cardId = request.getParameter("cardId");
        Payment payment = paymentDAO.findPayment(Long.parseLong(cardId));

        payment.setNumber(request.getParameter("number"));
        payment.setTill(request.getParameter("till"));
        payment.setCvv(request.getParameter("cvv"));

        paymentDAO.updatePayment(payment);
        return Path.PAGE__PAYMENTS;
    }
}
