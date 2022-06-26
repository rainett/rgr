package main.commands.payment;

import main.Path;
import main.commands.Command;
import main.db.dao.PaymentDAO;
import main.db.entities.Payment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowUpdatePaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int addressId = Integer.parseInt(request.getParameter("paymentId"));
        Payment payment = PaymentDAO.getInstance().getPayment(addressId);
        request.setAttribute("payment", payment);
        return Path.PAGE__UPDATE_PAYMENT;
    }
}
