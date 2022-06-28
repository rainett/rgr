package main.db.entities;

public enum Role {
    CLIENT("client"), MANAGER("client");

    Role(String role) {
        this.roleStr = role;
    }

    private final String roleStr;

    public String getRoleStr() {
        return this.roleStr;
    }

    public static Role getRole(String roleStr) {
        for (Role r : Role.values()) {
            if (r.roleStr.equals(roleStr)) {
                return r;
            }
        }
        return null;
    }
}