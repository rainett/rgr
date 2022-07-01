package main.commands.address;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.AddressDAO;

import javax.servlet.http.HttpServletRequest;

import static main.Controller.controller;


public class DeleteAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        AddressDAO.getInstance().deleteAddress(addressId);
        return controller + CommandName.COMMAND__ADDRESSES;
    }
}
