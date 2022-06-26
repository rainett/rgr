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

public class ShowUpdateAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        Address address = AddressDAO.getInstance().getAddress(addressId);
        request.setAttribute("address", address);
        return Path.PAGE__UPDATE_ADDRESS;
    }
}
