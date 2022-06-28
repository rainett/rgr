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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.Controller.controller;


public class NewPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("wrongNumber");
        session.removeAttribute("wrongTill");
        session.removeAttribute("wrongCvv");
        if (invalid(request, session)) {
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
            case "payments": return controller + CommandNames.COMMAND__PAYMENTS;
            case "order": return controller + CommandNames.COMMAND__SHOW_ORDER_PAYMENTS;
        }
        return Path.PAGE__START;
    }

    static boolean invalid(HttpServletRequest request, HttpSession session) {
        String number = request.getParameter("number");
        String till = request.getParameter("till");
        String cvv = request.getParameter("cvv");
        return !validateNumber(number, session) || !validateTill(till, session) || !validateCvv(cvv, session);
    }

    private static boolean validateCvv(String cvv, HttpSession session) {
        Pattern pattern = Pattern.compile("^[0-9]{3}$");
        Matcher matcher = pattern.matcher(cvv);
        if (!matcher.find()) {
            session.setAttribute("wrongCvv", "Неправильно введено CVV-картки");
            return false;
        }
        return true;
    }

    private static boolean validateTill(String till, HttpSession session) {
        Pattern pattern = Pattern.compile("(0[1-9]|1[0-2])/[0-9]{2}");
        Matcher matcher = pattern.matcher(till);
        if (!matcher.find()) {
            session.setAttribute("wrongTill", "Неправильно введено термін картки");
            return false;
        }
        return true;
    }

    private static boolean validateNumber(String number, HttpSession session) {
        Pattern pattern = Pattern.compile("^[0-9]{16}$");
        Matcher matcher = pattern.matcher(number);
        if (!matcher.find()) {
            session.setAttribute("wrongNumber", "Неправильно введено номер картки");
            return false;
        }
        return true;
    }
}
