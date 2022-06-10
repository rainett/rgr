package main.commands.address;

import main.Path;
import main.commands.Command;
import main.db.dao.AddressDAO;
import main.db.entities.Address;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class NewAddressCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("username");
        Address address = new Address();

        address.setApartmentNumber(request.getParameter("apartmentNumber").equals("") ? null : request.getParameter("apartmentNumber"));
        address.setHouseNumber(request.getParameter("houseNumber"));
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));

        new AddressDAO().newAddress(login, address);
        return Path.PAGE__ADDRESSES;
    }
}
