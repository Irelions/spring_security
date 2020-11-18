package web.service;

import org.hibernate.annotations.AttributeAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;
import web.repositories.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

////   @Autowired
//   private final UserDao userDao;
//
//   public UserDetailsServiceImpl(UserDao userDao) {
//      this.userDao = userDao;
//   }

   private UserRepository userRepository;

   @Autowired
   public void setUserRepository(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public User findByUsername(String username) {
      return userRepository.findByUsername(username);
   }



   // «Пользователь» – это просто Object. В большинстве случаев он может быть
   //  приведен к классу UserDetails.
   // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = findByUsername(username);
      if(user == null) {
         throw new UsernameNotFoundException("Error");
      }

      return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
              mapRolesToAuthorities(user.getRoles()));
//      return userDao.getUserByName(s);
   }

   private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
      return roles.stream().map(p -> new SimpleGrantedAuthority(p.getRole())).collect(Collectors.toList());
   }
}
