package web.service;

import org.springframework.stereotype.Service;
import web.model.User;

import java.util.List;

@Service
public interface UserService {
    void add(User user);
    List<User> listUsers();
    User showUser (int id);
    void delete(int id);
    List<User> findUserByUsername(String username);
}
