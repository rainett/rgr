package main.db.entities;

public class Application {
    private int id;
    private int userId;
    private int roleId;
    private boolean state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format(
                "Application[applicationId = %d, userId = %d, role = %s, state = %s]",
                id, userId, roleId, state
        );
    }
}
