package main.commands.payment;

import main.Path;
import main.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowNewPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String resp = request.getParameter("resp");
        request.setAttribute("resp", resp);
        return Path.PAGE__NEW_PAYMENT;
    }
}
