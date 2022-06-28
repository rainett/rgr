package main.commands.payment;

import main.Path;
import main.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowNewPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String resp = request.getParameter("resp");
        request.setAttribute("resp", resp);
        session.removeAttribute("wrongNumber");
        session.removeAttribute("wrongTill");
        session.removeAttribute("wrongCvv");
        return Path.PAGE__NEW_PAYMENT;
    }
}
