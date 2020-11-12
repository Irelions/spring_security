package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
   List<User> listUsers();
   void add(User user);
   User showUser (int id);
   void delete(int id);
   void update(int id, User user);

}
