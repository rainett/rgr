package main.commands.payment;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.PaymentDAO;

import javax.servlet.http.HttpServletRequest;

import static main.Controller.controller;


public class DeletePaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String cardId = request.getParameter("paymentId");
        PaymentDAO.getInstance().deletePayment(Long.parseLong(cardId));
        return controller + CommandName.COMMAND__PAYMENTS;
    }
}
