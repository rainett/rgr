package main.commands.order;

import main.Path;
import main.commands.Command;
import main.db.dao.AddressDAO;
import main.db.dao.PaymentDAO;
import main.db.entities.Address;
import main.db.entities.Order;
import main.db.entities.Payment;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ShowOrderAddressesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        List<Address> addresses = AddressDAO.getInstance().getUserAddresses(userId);
        request.setAttribute("addresses", addresses);
        return Path.PAGE__ORDER_ADDRESS;
    }
}
