package main.commands;

import main.commands.address.*;
import main.commands.application.*;
import main.commands.dish.*;
import main.commands.order.*;
import main.commands.payment.*;
import main.commands.user.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put(CommandName.COMMAND__ADDRESSES,            new AddressesCommand());
        commands.put(CommandName.COMMAND__NEW_ADDRESS,          new NewAddressCommand());
        commands.put(CommandName.COMMAND__UPDATE_ADDRESS,       new UpdateAddressCommand());
        commands.put(CommandName.COMMAND__DELETE_ADDRESS,       new DeleteAddressCommand());
        commands.put(CommandName.COMMAND__SHOW_UPDATE_ADDRESS,  new ShowUpdateAddressCommand());
        commands.put(CommandName.COMMAND__SHOW_NEW_ADDRESS,     new ShowNewAddressCommand());

        commands.put(CommandName.COMMAND__DISHES,               new DishesCommand());
        commands.put(CommandName.COMMAND__SHOW_EDIT_DISHES,     new ShowEditDishesCommand());
        commands.put(CommandName.COMMAND__SHOW_NEW_DISH,        new ShowNewDishCommand());
        commands.put(CommandName.COMMAND__NEW_DISH,             new NewDishCommand());
        commands.put(CommandName.COMMAND__SHOW_UPDATE_DISH,     new ShowUpdateDishCommand());
        commands.put(CommandName.COMMAND__UPDATE_DISH,          new UpdateDishCommand());

        commands.put(CommandName.COMMAND__ORDERS,               new OrdersCommand());
        commands.put(CommandName.COMMAND__SPECIFIC_ORDER,       new SpecificOrderCommand());
        commands.put(CommandName.COMMAND__ORDER_DISHES,         new NewOrderCommand());
        commands.put(CommandName.COMMAND__CONFIRM_ORDER,        new ConfirmOrderCommand());
        commands.put(CommandName.COMMAND__CANCEL_ORDER,         new CancelOrderCommand());
        commands.put(CommandName.COMMAND__SET_ORDER_ADDRESS,    new SetOrderAddressCommand());
        commands.put(CommandName.COMMAND__SET_ORDER_PAYMENT,    new SetOrderPaymentCommand());
        commands.put(CommandName.COMMAND__SHOW_ORDER_DISHES,    new ShowOrderDishesCommand());
        commands.put(CommandName.COMMAND__SHOW_ORDER_ADDRESSES, new ShowOrderAddressesCommand());
        commands.put(CommandName.COMMAND__SHOW_ORDER_PAYMENTS,  new ShowOrderPaymentsCommand());
        commands.put(CommandName.COMMAND__SHOW_ORDER_CONFIRM,   new ShowOrderConfirmCommand());


        commands.put(CommandName.COMMAND__PAYMENTS,             new PaymentsCommand());
        commands.put(CommandName.COMMAND__NEW_PAYMENT,          new NewPaymentCommand());
        commands.put(CommandName.COMMAND__UPDATE_PAYMENT,       new UpdatePaymentCommand());
        commands.put(CommandName.COMMAND__DELETE_PAYMENT,       new DeletePaymentCommand());
        commands.put(CommandName.COMMAND__SHOW_NEW_PAYMENT,     new ShowNewPaymentCommand());
        commands.put(CommandName.COMMAND__SHOW_UPDATE_PAYMENT,  new ShowUpdatePaymentCommand());

        commands.put(CommandName.COMMAND__LOGIN,                new LoginCommand());
        commands.put(CommandName.COMMAND__LOGOUT,               new LogoutCommand());
        commands.put(CommandName.COMMAND__REGISTER,             new RegisterCommand());
        commands.put(CommandName.COMMAND__USER,                 new UserCommand());
        commands.put(CommandName.COMMAND__SHOW_LOGIN,           new ShowLoginCommand());
        commands.put(CommandName.COMMAND__SHOW_REGISTER,        new ShowRegisterCommand());
        commands.put(CommandName.COMMAND__SHOW_SEND_APPLICATION,new ShowSendApplicationCommand());
        commands.put(CommandName.COMMAND__SEND_APPLICATION,     new SendApplicationCommand());
        commands.put(CommandName.COMMAND__SHOW_APPLICATIONS,    new ShowApplicationsCommand());
        commands.put(CommandName.COMMAND__SHOW_APPLICATION_PROCESS,new ShowApplicationProcessCommand());
        commands.put(CommandName.COMMAND__APPLICATION_PROCESS,  new ApplicationProcessCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return new UnknownCommand();
        }
        return commands.get(commandName);
    }

}
