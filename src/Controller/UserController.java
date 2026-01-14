/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Shahil
 */

import Model.User;
import java.util.HashMap;
import java.util.Map;

public class UserController {
    private Map<String, User> users = new HashMap<>();
    
    public UserController() {
        loadDefaultUsers();
    }
    
    private void loadDefaultUsers() {
        // Admin
        users.put("admin", new User("admin", "admin1234", User.ROLE_ADMIN));
        
        // Regular users
        users.put("user1", new User("user1", "user1", User.ROLE_USER));
        users.put("user2", new User("user2", "user2", User.ROLE_USER));
        users.put("user3", new User("user3", "user3", User.ROLE_USER));
        users.put("user4", new User("user4", "user4", User.ROLE_USER));
        users.put("user5", new User("user5", "user5", User.ROLE_USER));
    }
    
    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && !user.isBanned() && user.checkPassword(password)) {
            return user; // Valid login
        }
        return null; // Invalid or banned
    }
    
    public boolean banUser(String username) {
        User user = users.get(username);
        if (user != null && user.getRole() == User.ROLE_USER) {
            user.setBanned(true);
            return true;
        }
        return false;
    }
    
    public boolean unbanUser(String username) {
        User user = users.get(username);
        if (user != null) {
            user.setBanned(false);
            return true;
        }
        return false;
    }
    
    public boolean isUserBanned(String username) {
        User user = users.get(username);
        return user != null && user.isBanned();
    }
    
    public Map<String, User> getAllUsers() {
        return new HashMap<>(users);
    }
    
    public boolean addUser(String username, String password, String role) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password, role));
            return true;
        }
        return false;
    }
    
    public boolean deleteUser(String username) {
        if (users.containsKey(username) && 
            users.get(username).getRole() != User.ROLE_ADMIN) {
            users.remove(username);
            return true;
        }
        return false;
    }
}