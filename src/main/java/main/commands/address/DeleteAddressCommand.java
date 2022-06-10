package main.commands.address;

import main.Path;
import main.commands.Command;
import main.db.dao.AddressDAO;
import main.db.entities.Address;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String addressId = request.getParameter("addressId");
        new AddressDAO().deleteAddress(Long.parseLong(addressId));
        return Path.PAGE__ADDRESSES;
    }
}
