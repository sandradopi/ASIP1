package es.udc.lbd.asi.restexample.security;

 import java.util.Collections;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.GrantedAuthority;
 import org.springframework.security.core.authority.SimpleGrantedAuthority;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.stereotype.Component;
 import org.springframework.transaction.annotation.Transactional;

 import es.udc.lbd.asi.restexample.model.domain.User_;
 import es.udc.lbd.asi.restexample.model.repository.UserDAO;

 @Component
 public class MyUserDetailsService implements UserDetailsService {
     private final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

     @Autowired
     private UserDAO userDAO;

     @Override
     @Transactional(readOnly = true)
     public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
         User_ user = userDAO.findByLogin(login);
         if (user == null) {
             throw new UsernameNotFoundException("User " + login + " not found");
         }
         logger.info("Loaded user {} with authority {}", login, user.getAuthority().name());
         GrantedAuthority authority = new SimpleGrantedAuthority(user.getAuthority().name());
         return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
                 Collections.singleton(authority));
     }
 }