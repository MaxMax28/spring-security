package web.repository;

import web.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void updateUser(User user);

    User getUserById(long id);

    User getUserByName(String name);

    List<User> getAllUsers();

    void deleteUser(long id);
}
