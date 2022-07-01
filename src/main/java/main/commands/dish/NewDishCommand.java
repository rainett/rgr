package main.commands.dish;

import main.commands.Command;
import main.commands.CommandName;
import main.db.dao.DishDAO;
import main.db.dao.PhotoDAO;
import main.db.entities.Dish;
import main.db.entities.Photo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.io.IOException;

import static main.Controller.controller;

public class NewDishCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String dishName = request.getParameter("dishName");
        int dishCategoryId = Integer.parseInt(request.getParameter("dishCategory"));
        int dishPrice = Integer.parseInt(request.getParameter("dishPrice"));
        Part filePart = request.getPart("dishPic");
        Dish dish = new Dish();
        Photo photo = new Photo();
        photo.setPic(filePart.getInputStream());
        int photoId = PhotoDAO.getInstance().newPhoto(photo);
        dish.setName(dishName);
        dish.setPrice(dishPrice);
        dish.setCategoryId(dishCategoryId);
        dish.setPhotoId(photoId);
        DishDAO.getInstance().newDish(dish);
        return controller + CommandName.COMMAND__SHOW_EDIT_DISHES;
    }
}
