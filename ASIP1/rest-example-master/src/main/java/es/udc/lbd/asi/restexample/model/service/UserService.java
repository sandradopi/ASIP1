package es.udc.lbd.asi.restexample.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.udc.lbd.asi.restexample.model.domain.AdminUser;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.domain.UserNoti;
import es.udc.lbd.asi.restexample.model.exception.EmailIncorrect;
import es.udc.lbd.asi.restexample.model.exception.PasswordTooShort;
import es.udc.lbd.asi.restexample.model.exception.RequiredFieldsException;
import es.udc.lbd.asi.restexample.model.exception.UserLoginEmailExistsException;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.StatusDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.AdminUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserListUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTO;
import es.udc.lbd.asi.restexample.security.SecurityUtils;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService implements UserServiceInterface{

  @Autowired
    private UserDAO userDAO;
  
  @Autowired
  private StatusDAO statusDAO;
  
  @Autowired
  private MovieDAO movieDAO;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  		@PreAuthorize("hasAuthority('USER')")
		@Override
		public List<NormalUserDTO> findAll() {
			 return userDAO.findAll().stream().map(user -> new NormalUserDTO(user)).collect(Collectors.toList());}
		
		@PreAuthorize("hasAuthority('USER')")
		@Override
		public NormalUserDTO findById(Long idUser)  {
	   	 return new NormalUserDTO(userDAO.findById(idUser));
	   }
		
		@PreAuthorize("hasAuthority('USER')")
		@Override
		public NormalUserListUserDTO findByLoginContadores(String login)  {
			NormalUserListUserDTO u= new NormalUserListUserDTO(userDAO.findByLoginNormal(login));
			u.setCountVista(movieDAO.findByMovieUserVistaPendiente(u.getLogin(), TipoStatus.VISTA));
			u.setCountPendiente(movieDAO.findByMovieUserVistaPendiente(u.getLogin(),TipoStatus.PENDIENTE));
			u.setCountValoration(movieDAO.findByMovieUserVistaValoration(u.getLogin()));
	   	return u;
	   }
		
	     @Transactional(readOnly = false)
	     @Override
		 public void registerUser(String login, String email,String password, UserNoti noti) throws UserLoginEmailExistsException, ParseException, RequiredFieldsException, PasswordTooShort, EmailIncorrect {
	    
	    		 Date ahora = new Date();
	    	     SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	    	     String actualDate= formateador.format(ahora);
	    	     Date data = formateador.parse(actualDate);
	    	     String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
	    	    	      "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
	    	     
	    	     Pattern pattern = Pattern.compile(emailPattern);
	    	     
	    	     //Comprobaciones Servicio del Registro
	    	     
	    	    if(login == null){ //Login vacio
		        	  throw new RequiredFieldsException("The login is a required field");
		         }
		        else if (userDAO.findByLogin(login) != null) {
		             throw new UserLoginEmailExistsException("User login " + login + " already exists");//login existente
		        }
		        
		        if (email== null){//Que el email esté vacio
		        	 throw new RequiredFieldsException("The email is a required field");
		        }else if (userDAO.findByEmail(email) != null) {
		             throw new UserLoginEmailExistsException("The email " +email + " already exists"); //email existente
		        } else{
	    	    	 Matcher matcher = pattern.matcher(email);
	    	    	 if (!matcher.matches()){
	    	    	 throw new EmailIncorrect("The email: "+ email +" don´t have the good format, review it ");
	    	    	 }
	    	   
		        }
	    	    
		       
		       if (password == null){ //password vacio
		        	  throw new RequiredFieldsException("The password is a required field");
		        	  
		        }else if(password.length()<4){ //Password muy corta
		        	throw new PasswordTooShort("The password is too short, minimum 4 letters please");
		        }

	    	     registerUser(login,email, password, false, data, noti);
	     }
	     
	     @Transactional(readOnly = false)
	     @Override
	     public void registerUser(String login,String email,String password, boolean isAdmin, Date data, UserNoti noti) throws UserLoginEmailExistsException, RequiredFieldsException, PasswordTooShort {
	       
	        
	         String encryptedPassword = passwordEncoder.encode(password);

	         if (isAdmin) {
	        	 AdminUser user = new AdminUser();
	        	 user.setLogin(login);
	        	 user.setPassword(encryptedPassword);
	        	 user.setAuthority(UserAuthority.ADMIN);
		         user.setEmail(email);
		         user.setData(data);
		         userDAO.save(user);
	         }else{
	        	 NormalUser user = new NormalUser();
	        	 user.setNotification(noti);
		         user.setLogin(login);
	        	 user.setPassword(encryptedPassword);
		         user.setAuthority(UserAuthority.USER);
		         user.setEmail(email);
		         user.setData(data);
		         userDAO.save(user);
		         }

	         
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

	    @PreAuthorize("hasAuthority('USER')")
		@Override
		public List<NormalUserListUserDTO> findAllContadores() {
			
			List <NormalUserListUserDTO> usuario = userDAO.findAllNoAdmin().stream().map(user -> new NormalUserListUserDTO(user)).collect(Collectors.toList());
			for(NormalUserListUserDTO u: usuario){
				u.setCountVista(movieDAO.findByMovieUserVistaPendiente(u.getLogin(), TipoStatus.VISTA));
				u.setCountPendiente(movieDAO.findByMovieUserVistaPendiente(u.getLogin(),TipoStatus.PENDIENTE));
				u.setCountValoration(movieDAO.findByMovieUserVistaValoration(u.getLogin()));
				
			}
			return usuario;
		}
	    
	    @PreAuthorize("hasAuthority('USER')")
	    @Transactional(readOnly = false)
	    @Override
	    public void update(String login, String noti){
	    	NormalUser bdUser = userDAO.findByLoginNormal(login);
	    	if(noti.equals("no")){
	    		bdUser.setNotification(null);
	    	}else if(noti.equals("EMAIL")){
	    		bdUser.setNotification(UserNoti.EMAIL);
	    	}else if (noti.equals("SMS")){
	    		bdUser.setNotification(UserNoti.SMS);
	    	}
	        userDAO.save(bdUser);
	        
	        }



		
	     
   

}
