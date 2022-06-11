package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class NewPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("username");
        Payment payment = new Payment();

        payment.setNumber(request.getParameter("number"));
        payment.setTill(request.getParameter("till"));
        payment.setCvv(request.getParameter("cvv"));

        new PaymentDAO().newPayment(login, payment);
        return session.getAttribute("order") == null ? Path.PAGE__PAYMENTS : Path.PAGE__ORDER_PAYMENTS;
    }
}
