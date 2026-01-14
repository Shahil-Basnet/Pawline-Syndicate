/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Shahil
 */
public class User {

    private String username;
    private String password;
    private boolean isBanned;
    private String role; // "ADMIN" or "USER"

    // Role constants as array
    public static final String[] roles = {"ADMIN", "USER"};
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        // Validate role
        this.role = isValidRole(role) ? role : ROLE_USER;
        this.isBanned = false;
    }

    private boolean isValidRole(String role) {
        for (String validRole : roles) {
            if (validRole.equals(role)) {
                return true;
            }
        }
        return false;
    }

    // Check if user is admin
    public boolean isAdmin() {
        return ROLE_ADMIN.equals(role);
    }

    // Check if user is regular user
    public boolean isRegularUser() {
        return ROLE_USER.equals(role);
    }

    // Getters and setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (isValidRole(role)) {
            this.role = role;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
