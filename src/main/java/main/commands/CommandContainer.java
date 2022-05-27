package main.commands;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return null;
        }
        return commands.get(commandName);
    }

}
