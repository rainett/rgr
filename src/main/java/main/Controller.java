package main;

import main.commands.Command;
import main.commands.CommandContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter("command");
        Command command = CommandContainer.get(commandName);
        String forward = command.execute(request, response);
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }

}
