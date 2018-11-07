package es.udc.lbd.asi.restexample.repository.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.StatusDAO;
import es.udc.lbd.asi.restexample.model.service.MovieService;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;


@Component
@EnableTransactionManagement
@Transactional
public class ScheduledTask {
	@Autowired
	MovieService movieService;
	@Autowired
	UserService userService;
	@Autowired
	StatusDAO statusDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	MovieDAO movieDAO;
	private final Properties properties = new Properties();
	private String password;
	private Session session;

	private void init() {
		properties.put("mail.smtp.host", "mail.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.user", "guajndos@gmail.com");
		properties.put("mail.smtp.auth", "true");
 
		session = Session.getDefaultInstance(properties);
	}
	
    @Scheduled(fixedRate = 86400)
    public void reportCurrentTime() throws AddressException, MessagingException {
    	init();
    	Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        String actualDate= formateador.format(ahora);
        
    	for (Status state : statusDAO.findAll()) {
    	    if (state.getType().equals(TipoStatus.PENDIENTE)){
    	    	Movie movie=state.getMovie();
    	    	NormalUser usuarioNormal = state.getNormalUser();
    	    	if(movie.getData().toString().equals(actualDate)){
    	    	try{
	    	    		MimeMessage message = new MimeMessage(session);
	    	    		message.setFrom(new InternetAddress("guajndos@gmail.com"));
	    				message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuarioNormal.getEmail()));
	    				message.setSubject("Hi! The movie "+ state.getMovie().getName() +" is now available.");
	    				message.setText("Good morning Dear customer "+ state.getNormalUser().getLogin() +" this email is to let you know that the film "
	    				+state.getMovie().getName()+" that you kept in your list of Pending Movies has been released today, so please sign"
	    				+ " in to our page, go and make some popcorn and give it to play.");
	    				Transport t = session.getTransport("smtp");
	    				t.connect("guajndos@gmail.com","deportivo13");
	    				t.sendMessage(message, message.getAllRecipients());
	    				t.close();
	    		}catch (MessagingException me){
	    			return;
	    				}
    	    		
    	    	}
    	    }
    	}
    }
}