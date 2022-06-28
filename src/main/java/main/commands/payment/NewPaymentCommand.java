package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.Controller.controller;


public class NewPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("wrongNumber");
        if (!validateNumber(request.getParameter("number"), session)) {
            return Path.PAGE__NEW_PAYMENT;
        }

        int userId = ((User) session.getAttribute("user")).getId();
        Payment payment = new Payment();

        payment.setNumber(request.getParameter("number"));
        payment.setTill(request.getParameter("till"));
        payment.setCvv(request.getParameter("cvv"));
        payment.setUserId(userId);

        PaymentDAO.getInstance().newPayment(payment);
        String resp = request.getParameter("resp");
        switch(resp) {
            case "payments": return controller + CommandName.COMMAND__PAYMENTS;
            case "order": return controller + CommandName.COMMAND__SHOW_ORDER_PAYMENTS;
        }
        return Path.PAGE__START;
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
