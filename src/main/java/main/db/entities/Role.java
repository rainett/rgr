package main.db.entities;

import java.util.Locale;

public enum Role {
    CLIENT, MANAGER, COOK, COURIER;

    public String getRoleName() {
        return this.name().substring(0, 1).toUpperCase(Locale.ROOT)
                + this.name().substring(1).toLowerCase(Locale.ROOT);
    }

    public static Role getRole(int roleId) {
        return Role.values()[roleId];
    }
}
