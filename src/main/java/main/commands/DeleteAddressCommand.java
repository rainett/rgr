package main.commands;

import main.Path;
import main.db.dao.AddressDAO;
import main.db.entities.Address;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AddressDAO addressDAO = new AddressDAO();
        String addressId = request.getParameter("addressId");
        addressDAO.deleteAddress(Long.parseLong(addressId));
        return Path.PAGE__ADDRESSES;
    }
}
