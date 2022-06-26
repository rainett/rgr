package main.commands.order;

import main.Path;
import main.commands.Command;
import main.db.dao.DishesDAO;
import main.db.dao.PaymentDAO;
import main.db.entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class ShowOrderConfirmCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.PAGE__CONFIRM_ORDER;
    }
}
