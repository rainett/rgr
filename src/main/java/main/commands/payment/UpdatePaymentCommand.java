package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.commands.CommandNames;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class UpdatePaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("wrongNumber");
        session.removeAttribute("wrongTill");
        session.removeAttribute("wrongCvv");
        if (NewPaymentCommand.invalid(request, session)) {
            return Path.PAGE__UPDATE_PAYMENT;
        }


        int paymentId = Integer.parseInt(request.getParameter("paymentId"));
        Payment payment = PaymentDAO.getInstance().getPayment(paymentId);

        payment.setNumber(request.getParameter("number"));
        payment.setTill(request.getParameter("till"));
        payment.setCvv(request.getParameter("cvv"));

        PaymentDAO.getInstance().updatePayment(payment);
        return controller + CommandNames.COMMAND__PAYMENTS;
    }
}
