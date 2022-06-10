package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.db.dao.PaymentDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeletePaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cardId = request.getParameter("cardId");
        new PaymentDAO().deletePayment(Long.parseLong(cardId));
        return Path.PAGE__PAYMENTS;
    }
}
