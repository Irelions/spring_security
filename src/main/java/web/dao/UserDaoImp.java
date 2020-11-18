package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import web.model.Role;
import web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Transient;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


@Repository
public class UserDaoImp implements UserDao {

   private final Map<String, User> userMap = Collections.singletonMap("ADMIN",
           new User(1L, "Evgeniy", "Ivanov", "IT", "ir@mail.ru", "ADMIN", "ADMIN", Collections.singleton(new Role(1L, "ROLE_ADMIN")))); // name - уникальное значение, выступает в качестве ключа Map
//           new User(1L, "USER", "USER", Collections.singleton(new Role(2L, "ROLE_USER")))); // name - уникальное значение, выступает в качестве ключа Map


   @PersistenceContext
//   @Autowired
   private EntityManager entityManager;

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return entityManager.createQuery("FROM User").getResultList();
   }

   @Override
   public void add(User user) {
      if(user.getId() == null) {
         entityManager.persist(user);
      } else entityManager.merge(user);
   }

   @Override
   public User showUser(int id) {
      return entityManager.find(User.class, (long)id);
   }

//   @Override
//   @SuppressWarnings("unchecked")
//   public List<User> findUserByUsername(String username) {
//      Query query = entityManager.createQuery("FROM User WHERE username = :username");
//      query.setParameter("username", username);
//      return query.getResultList();
////      return entityManager.createQuery("FROM User WHERE username = username", User.class).getSingleResult();
//   }

   @Override
   public User getUserByName(String name) {
      if (!userMap.containsKey(name)) {
         return null;
      }
      return userMap.get(name);
   }

   @Override
   public void delete(int id) {
      entityManager.remove(entityManager.find(User.class, (long)id));
   }
}
