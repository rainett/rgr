package main.commands;

import main.commands.address.DeleteAddressCommand;
import main.commands.address.NewAddressCommand;
import main.commands.address.UpdateAddressCommand;
import main.commands.order.CancelOrderCommand;
import main.commands.order.ConfirmOrderCommand;
import main.commands.order.OrderDishesCommand;
import main.commands.payment.DeletePaymentCommand;
import main.commands.payment.NewPaymentCommand;
import main.commands.payment.UpdatePaymentCommand;
import main.commands.user.LoginCommand;
import main.commands.user.LogoutCommand;
import main.commands.user.RegisterCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("login",           new LoginCommand());
        commands.put("register",        new RegisterCommand());
        commands.put("logout",          new LogoutCommand());
        commands.put("updateAddress",   new UpdateAddressCommand());
        commands.put("deleteAddress",   new DeleteAddressCommand());
        commands.put("newAddress",      new NewAddressCommand());
        commands.put("updatePayment",   new UpdatePaymentCommand());
        commands.put("deletePayment",   new DeletePaymentCommand());
        commands.put("newPayment",      new NewPaymentCommand());
        commands.put("orderDishes",     new OrderDishesCommand());
        commands.put("cancelOrder",     new CancelOrderCommand());
        commands.put("confirmOrder",    new ConfirmOrderCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return null;
        }
        return commands.get(commandName);
    }

}
