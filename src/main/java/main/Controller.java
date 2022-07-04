package main;

import main.commands.Command;
import main.commands.CommandContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig(maxFileSize = 16177215)
public class Controller extends HttpServlet {

    public static final String controller = "controller?command=";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = getForward(request);
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = getForward(request);
        if (forward != null) {
            response.sendRedirect(forward);
        }
    }

    private String getForward(HttpServletRequest request) throws IOException, ServletException {
        String commandName = request.getParameter("command");
        Command command = CommandContainer.get(commandName);
        return command.execute(request);
    }

}
