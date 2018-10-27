package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.udc.lbd.asi.restexample.model.domain.User;
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

    public UserDTO findByLogin(String login){
        return new UserDTO(userDAO.findByLogin(login));
    }
    

	@Override
	public List<NormalUserDTO> findAll() {
		 return userDAO.findAll().stream().map(user -> new NormalUserDTO(user)).collect(Collectors.toList());}

		 public void registerUser(String login, String password) throws UserLoginExistsException {
	         registerUser(login, password, false);
	     }

	     public void registerUser(String login, String password, boolean isAdmin) throws UserLoginExistsException {
	         if (userDAO.findByLogin(login) != null) {
	             throw new UserLoginExistsException("User login " + login + " already exists");
	         }

	         User user = new User();
	         String encryptedPassword = passwordEncoder.encode(password);

	         user.setLogin(login);
	         user.setPassword(encryptedPassword);
	         user.setAuthority(UserAuthority.USER);
	         if (isAdmin) {
	             user.setAuthority(UserAuthority.ADMIN);
	         }

	         userDAO.save(user);
	     }
	     
	     public AdminUserDTO getCurrentUserWithAuthority() {
	         String currentUserLogin = SecurityUtils.getCurrentUserLogin();
	         if (currentUserLogin != null) {
	             return new AdminUserDTO(userDAO.findByLogin(currentUserLogin));
	         }
	         return null;
	     }
   

}
