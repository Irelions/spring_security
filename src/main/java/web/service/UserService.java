package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User showUser (int id);
    void delete(int id);
    void update(int id, User user);
}
