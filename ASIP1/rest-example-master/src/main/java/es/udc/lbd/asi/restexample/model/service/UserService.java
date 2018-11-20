package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.udc.lbd.asi.restexample.model.domain.AdminUser;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.AdminUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTO;
import es.udc.lbd.asi.restexample.security.SecurityUtils;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService implements UserServiceInterface{

  @Autowired
    private UserDAO userDAO;
  
  @Autowired
  private PasswordEncoder passwordEncoder;


	@Override
	public List<NormalUserDTO> findAll() {
		 return userDAO.findAll().stream().map(user -> new NormalUserDTO(user)).collect(Collectors.toList());}
	
		
	     @Transactional(readOnly = false)
		 public void registerUser(String login, String email,String password) throws UserLoginExistsException {
	         registerUser(login,email, password, false);
	     }
	     
	     @Transactional(readOnly = false)
	     public void registerUser(String login,String email,String password, boolean isAdmin) throws UserLoginExistsException {
	         if (userDAO.findByLogin(login) != null) {
	             throw new UserLoginExistsException("User login " + login + " already exists");
	         }
	         String encryptedPassword = passwordEncoder.encode(password);

	         if (isAdmin) {
	        	 AdminUser user = new AdminUser();
	        	 user.setLogin(login);
	        	 user.setPassword(encryptedPassword);
	        	 user.setAuthority(UserAuthority.ADMIN);
		         user.setEmail(email);
		         userDAO.save(user);
	         }else{
	         NormalUser user = new NormalUser();
	         user.setLogin(login);
        	 user.setPassword(encryptedPassword);
	         user.setAuthority(UserAuthority.USER);
	         user.setEmail(email);
	         userDAO.save(user);}

	         
	     }
	     
	     public AdminUserDTO getCurrentUserWithAuthority() {
	         String currentUserLogin = SecurityUtils.getCurrentUserLogin();
	         if (currentUserLogin != null) {
	             return new AdminUserDTO(userDAO.findByLogin(currentUserLogin));
	         }
	         return null;
	     }
	     
	     public NormalUserDTO getCurrentUserWithoutAuthority() {
	         String currentUserLogin = SecurityUtils.getCurrentUserLogin();
	         if (currentUserLogin != null) {
	             return new NormalUserDTO(userDAO.findByLogin(currentUserLogin));
	         }
	         return null;
	     }
	     public UserDTO getCurrentUserWithAuthorityUser() {
	         String currentUserLogin = SecurityUtils.getCurrentUserLogin();
	         if (currentUserLogin != null) {
	             return new UserDTO(userDAO.findByLogin(currentUserLogin));
	         }
	         return null;
	     }



		
	     
   

}
