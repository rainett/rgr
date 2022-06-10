package main.commands.address;

import main.Path;
import main.commands.Command;
import main.db.dao.AddressDAO;
import main.db.entities.Address;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AddressDAO addressDAO = new AddressDAO();
        String addressId = request.getParameter("addressId");
        Address address = addressDAO.findAddress(Long.parseLong(addressId));

        address.setApartmentNumber(request.getParameter("apartmentNumber").equals("") ? null : request.getParameter("apartmentNumber"));
        address.setHouseNumber(request.getParameter("houseNumber"));
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));

        addressDAO.updateAddress(address);
        return Path.PAGE__ADDRESSES;
    }
}
