package data.service;

import models.User;

public interface UserDAO {

    Integer addUser(String username, String password, String role);

    void listUsers();

    void updateUser(Integer userID, String username, String password, String role);

    void deleteUser(Integer userID);

    User findByUsername(String username);
}
