package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.commands.CommandNames;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class NewPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        Payment payment = new Payment();

        payment.setNumber(request.getParameter("number"));
        payment.setTill(request.getParameter("till"));
        payment.setCvv(request.getParameter("cvv"));
        payment.setUserId(userId);

        PaymentDAO.getInstance().newPayment(payment);
        String resp = request.getParameter("resp");
        switch(resp) {
            case "payments": return controller + CommandNames.COMMAND__PAYMENTS;
            case "order": return controller + CommandNames.COMMAND__SHOW_ORDER_PAYMENTS;
        }
        return Path.PAGE__START;
    }
}
