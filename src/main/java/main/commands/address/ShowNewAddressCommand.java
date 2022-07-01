package main.commands.address;

import main.Path;
import main.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowNewAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String resp = request.getParameter("resp");
        request.setAttribute("resp", resp);
        return Path.PAGE__NEW_ADDRESS;
    }
}
