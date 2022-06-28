package main.commands.order;

import main.Path;
import main.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowOrderConfirmCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.PAGE__CONFIRM_ORDER;
    }
}
