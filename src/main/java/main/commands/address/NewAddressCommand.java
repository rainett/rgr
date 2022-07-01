package main.commands.address;

import main.Path;
import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.AddressDAO;
import main.db.entities.Address;
import main.db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static main.Controller.controller;


public class NewAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute("user")).getId();
        Address address = new Address();
        setAddressFields(request, userId, address);
        AddressDAO.getInstance().newAddress(address);

        String resp = request.getParameter("resp");
        switch(resp) {
            case "addresses": return controller + CommandName.COMMAND__ADDRESSES;
            case "order": return controller + CommandName.COMMAND__SHOW_ORDER_ADDRESSES;
        }
        return Path.PAGE__START;
    }

    private void setAddressFields(HttpServletRequest request, int userId, Address address) {
        address.setApartmentNumber(request.getParameter("apartmentNumber").equals("") ? null : request.getParameter("apartmentNumber"));
        address.setHouseNumber(request.getParameter("houseNumber"));
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
        address.setUserId(userId);
    }
}
