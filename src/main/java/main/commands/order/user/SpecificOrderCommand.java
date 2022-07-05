package main.commands.order.user;

import main.Path;
import main.commands.Command;
import main.commands.order.manager.ShowSpecificOrderManagerCommand;

import javax.servlet.http.HttpServletRequest;

public class SpecificOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ShowSpecificOrderManagerCommand.showSpecificOrder(request);
        return Path.PAGE__SPECIFIC_ORDER;
    }
}
