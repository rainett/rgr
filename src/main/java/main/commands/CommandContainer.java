package main.commands;

import main.commands.address.*;
import main.commands.dish.DishesCommand;
import main.commands.order.*;
import main.commands.payment.*;
import main.commands.user.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put(CommandNames.COMMAND__ADDRESSES,           new AddressesCommand());
        commands.put(CommandNames.COMMAND__NEW_ADDRESS,         new NewAddressCommand());
        commands.put(CommandNames.COMMAND__UPDATE_ADDRESS,      new UpdateAddressCommand());
        commands.put(CommandNames.COMMAND__DELETE_ADDRESS,      new DeleteAddressCommand());
        commands.put(CommandNames.COMMAND__SHOW_UPDATE_ADDRESS, new ShowUpdateAddressCommand());
        commands.put(CommandNames.COMMAND__SHOW_NEW_ADDRESS,    new ShowNewAddressCommand());

        commands.put(CommandNames.COMMAND__DISHES,              new DishesCommand());

        commands.put(CommandNames.COMMAND__ORDERS,              new OrdersCommand());
        commands.put(CommandNames.COMMAND__SPECIFIC_ORDER,      new SpecificOrderCommand());
        commands.put(CommandNames.COMMAND__ORDER_DISHES,        new NewOrderCommand());
        commands.put(CommandNames.COMMAND__CONFIRM_ORDER,       new ConfirmOrderCommand());
        commands.put(CommandNames.COMMAND__CANCEL_ORDER,        new CancelOrderCommand());
        commands.put(CommandNames.COMMAND__SET_ORDER_ADDRESS,   new SetOrderAddressCommand());
        commands.put(CommandNames.COMMAND__SET_ORDER_PAYMENT,   new SetOrderPaymentCommand());
        commands.put(CommandNames.COMMAND__SHOW_ORDER_DISHES,   new ShowOrderDishesCommand());
        commands.put(CommandNames.COMMAND__SHOW_ORDER_ADDRESSES,new ShowOrderAddressesCommand());
        commands.put(CommandNames.COMMAND__SHOW_ORDER_PAYMENTS, new ShowOrderPaymentsCommand());
        commands.put(CommandNames.COMMAND__SHOW_ORDER_CONFIRM,  new ShowOrderConfirmCommand());


        commands.put(CommandNames.COMMAND__PAYMENTS,            new PaymentsCommand());
        commands.put(CommandNames.COMMAND__NEW_PAYMENT,         new NewPaymentCommand());
        commands.put(CommandNames.COMMAND__UPDATE_PAYMENT,      new UpdatePaymentCommand());
        commands.put(CommandNames.COMMAND__DELETE_PAYMENT,      new DeletePaymentCommand());
        commands.put(CommandNames.COMMAND__SHOW_NEW_PAYMENT,    new ShowNewPaymentCommand());
        commands.put(CommandNames.COMMAND__SHOW_UPDATE_PAYMENT, new ShowUpdatePaymentCommand());

        commands.put(CommandNames.COMMAND__LOGIN,               new LoginCommand());
        commands.put(CommandNames.COMMAND__LOGOUT,              new LogoutCommand());
        commands.put(CommandNames.COMMAND__REGISTER,            new RegisterCommand());
        commands.put(CommandNames.COMMAND__USER,                new UserCommand());
        commands.put(CommandNames.COMMAND__SHOW_LOGIN,          new ShowLoginCommand());
        commands.put(CommandNames.COMMAND__SHOW_REGISTER,       new ShowRegisterCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return new UnknownCommand();
        }
        return commands.get(commandName);
    }

}
