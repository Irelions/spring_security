package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
//   @Autowired
   private EntityManager sessionFactory;

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return sessionFactory.createQuery("FROM User").getResultList();
   }

   @Override
   public void add(User user) {
      if(user.getId() == null) {
         sessionFactory.persist(user);
      } else sessionFactory.merge(user);
   }

   @Override
   public User showUser(int id) {
      return sessionFactory.find(User.class, (long)id);
   }

   @Override
   public void delete(int id) {
      sessionFactory.remove(sessionFactory.find(User.class, (long)id));
   }

   @Override
   public void update(int id, User user) {
   }
}
