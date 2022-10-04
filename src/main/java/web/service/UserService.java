package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(int id, User updUser);
    List<User> getUserList();

    User getUserById(int id);

}
