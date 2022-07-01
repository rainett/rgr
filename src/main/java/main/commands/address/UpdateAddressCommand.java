package main.commands.address;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.AddressDAO;
import main.db.entities.Address;

import javax.servlet.http.HttpServletRequest;

import static main.Controller.controller;


public class UpdateAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        Address address = AddressDAO.getInstance().getAddress(addressId);
        setAddressFields(request, address);
        AddressDAO.getInstance().updateAddress(address);
        return controller + CommandName.COMMAND__ADDRESSES;
    }

    private void setAddressFields(HttpServletRequest request, Address address) {
        address.setApartmentNumber(request.getParameter("apartmentNumber").equals("") ? null : request.getParameter("apartmentNumber"));
        address.setHouseNumber(request.getParameter("houseNumber"));
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
    }
}
