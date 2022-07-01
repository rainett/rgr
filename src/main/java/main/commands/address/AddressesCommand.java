package main.commands.address;

import main.Path;
import main.commands.Command;
import main.db.dao.AddressDAO;
import main.db.entities.Address;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddressesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = ((User) session.getAttribute("user"));
        List<Address> addresses = AddressDAO.getInstance().getUserAddresses(user.getId());
        request.setAttribute("addresses", addresses);
        return Path.PAGE__ADDRESSES;
    }
}
