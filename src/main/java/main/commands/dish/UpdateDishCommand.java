package main.commands.dish;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.DishDAO;
import main.db.dao.PhotoDAO;
import main.db.entities.Dish;
import main.db.entities.Photo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;

import static main.Controller.controller;

public class UpdateDishCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dishId = Integer.parseInt(request.getParameter("dishId"));
        String dishName = request.getParameter("dishName");
        String dishCategory = request.getParameter("dishCategory");
        int dishPrice = Integer.parseInt(request.getParameter("dishPrice"));
        Part filePart = request.getPart("dishPic");
        Dish dish = DishDAO.getInstance().getDish(dishId);
        dish.setName(dishName);
        dish.setPrice(dishPrice);
        if (dishCategory != null)
            dish.setCategory(dishCategory);
        DishDAO.getInstance().updateDish(dish);
        if (filePart.getSize() != 0) {
            Photo photo = new Photo();
            photo.setPic(filePart.getInputStream());
            photo.setId(dish.getPhotoID());
            PhotoDAO.getInstance().updatePhoto(photo);
        }
        return controller + CommandName.COMMAND__SHOW_EDIT_DISHES;
    }

}
