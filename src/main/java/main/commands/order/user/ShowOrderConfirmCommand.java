package main.commands.order.user;

import main.Path;
import main.commands.Command;

import javax.servlet.http.HttpServletRequest;


public class ShowOrderConfirmCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return Path.PAGE__CONFIRM_ORDER;
    }
}
