package main.commands.payment;

import main.Path;
import main.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowNewPaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String resp = request.getParameter("resp");
        request.setAttribute("resp", resp);
        session.removeAttribute("wrongNumber");
        return Path.PAGE__NEW_PAYMENT;
    }
}
