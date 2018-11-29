package es.udc.lbd.asi.restexample.repository.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
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
import es.udc.lbd.asi.restexample.model.domain.UserNoti;
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
	
	private Properties properties = new Properties();
	private List<Movie> movies= null;
	private List<Status> status=null;
	private Session session;
	private final static Logger log = Logger.getLogger("SMS");

	private void init() {
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.port","587");
		properties.setProperty("mail.smtp.user", "marsusanez@gmail.com");
		properties.setProperty("mail.smtp.auth", "true");
 
		session = Session.getDefaultInstance(properties);
	}
	
    @Scheduled(cron = "0 50 11 * * * ")
    public void reportCurrentTime() throws AddressException, MessagingException, ParseException {
    	init();
    	Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        String actualDate= formateador.format(ahora);
        Date fecha = formateador.parse(actualDate);
        
        movies =movieDAO.findAllDatePendiente(fecha);
        
        if(movies !=null){
        for (Movie movie: movies){
        		status= statusDAO.findByMovies(movie);
        		for(Status state: status){
    	    	NormalUser usuarioNormal = state.getNormalUser();
    	    	if(usuarioNormal.getNotification()== UserNoti.EMAIL){
    		
	    	    	try{
		    	    		MimeMessage message = new MimeMessage(session);
		    	    		message.setFrom(new InternetAddress("marsusanez@gmail.com"));
		    				message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuarioNormal.getEmail()));
		    				message.setSubject("Hi! The movie "+ state.getMovie().getName() +" is now available.");
		    				message.setText("Hi Mr/Mrs "+ state.getNormalUser().getLogin() +" :"+"\n" +"This email is to let you know that the film "
		    				+state.getMovie().getName()+" that you kept in your list of Pending Movies has been released today.\n" +"So please sign "
		    				+ "in , make some popcorn and give it to play :D" +"\n"+"\n"+ "Best Wishes, your favorite app of Movies Online <3");
		    				Transport t = session.getTransport("smtp");
		    				t.connect("marsusanez@gmail.com","asiasi2018");
		    				t.sendMessage(message, message.getAllRecipients());
		    				t.close();
		    		}catch (MessagingException me){
		    			return;
		    				}
    	    			} else if(usuarioNormal.getNotification()== UserNoti.SMS) {
    	    				log.info("\n" +"Hi Mr/Mrs "+ state.getNormalUser().getLogin() +" :"+"\n" +"This email is to let you know that the film "
    			    				+state.getMovie().getName()+" that you kept in your list of Pending Movies has been released today.\n" +"So please sign "
    			    				+ "in , make some popcorn and give it to play :D" +"\n"+ "Best Wishes, your favorite app of Movies Online <3"+"\n");
    	    			}
        			}
        		}
    	    }
    	}
    }
