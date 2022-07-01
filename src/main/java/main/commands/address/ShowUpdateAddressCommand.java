package main.commands.address;

import main.Path;
import main.commands.Command;
import main.db.dao.AddressDAO;
import main.db.entities.Address;

import javax.servlet.http.HttpServletRequest;

public class ShowUpdateAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        Address address = AddressDAO.getInstance().getAddress(addressId);
        request.setAttribute("address", address);
        return Path.PAGE__UPDATE_ADDRESS;
    }
}
