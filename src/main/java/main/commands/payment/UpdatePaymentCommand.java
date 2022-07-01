package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class UpdatePaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("wrongNumber");
        if (validateNumber(request.getParameter("number"), session)) {
            return Path.PAGE__UPDATE_PAYMENT;
        }


        int paymentId = Integer.parseInt(request.getParameter("paymentId"));
        Payment payment = PaymentDAO.getInstance().getPayment(paymentId);

        payment.setNumber(request.getParameter("number"));
        payment.setTill(request.getParameter("till"));
        payment.setCvv(request.getParameter("cvv"));

        PaymentDAO.getInstance().updatePayment(payment);
        return controller + CommandName.COMMAND__PAYMENTS;
    }

    private static boolean validateNumber(String number, HttpSession session) {
        Payment payment = PaymentDAO.getInstance().getPayment(number);
        if (payment != null) {
            session.setAttribute("wrongNumber", "Ви намагаєтесь додати існуючу картку");
            return false;
        }
        return true;
    }
}
