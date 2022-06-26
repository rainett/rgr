package main.commands.payment;

import main.commands.Command;
import main.commands.CommandNames;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static main.Controller.controller;


public class UpdatePaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int cardId = Integer.parseInt(request.getParameter("cardId"));
        Payment payment = PaymentDAO.getInstance().getPayment(cardId);

        payment.setNumber(request.getParameter("number"));
        payment.setTill(request.getParameter("till"));
        payment.setCvv(request.getParameter("cvv"));

        PaymentDAO.getInstance().updatePayment(payment);
        return controller + CommandNames.COMMAND__PAYMENTS;
    }
}
