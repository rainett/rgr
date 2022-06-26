package main.commands.address;

import main.Path;
import main.commands.Command;
import main.db.dao.AddressDAO;
import main.db.entities.Address;
import main.db.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddressesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = ((User) session.getAttribute("user"));
        List<Address> addresses = AddressDAO.getInstance().getUserAddresses(user.getId());
        request.setAttribute("addresses", addresses);
        return Path.PAGE__ADDRESSES;
    }
}
