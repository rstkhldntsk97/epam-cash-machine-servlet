package ua.rstkhldntsk.servlet.model;

/**
 * contains user roles in application
 */
public enum Role {

    CASHIER, SENIOR_CASHIER, COMMODITY_EXPERT, ADMIN;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

}
