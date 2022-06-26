package main.commands.payment;

import main.commands.Command;
import main.commands.CommandNames;
import main.db.dao.PaymentDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static main.Controller.controller;


public class DeletePaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cardId = request.getParameter("paymentId");
        PaymentDAO.getInstance().deletePayment(Long.parseLong(cardId));
        return controller + CommandNames.COMMAND__PAYMENTS;
    }
}
