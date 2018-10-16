package es.udc.lbd.asi.restexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class Inicialization implements ApplicationRunner{
	
	
	@Autowired
	  private MovieServiceClient movieServiceClient;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		movieServiceClient.showMovies();
		
	}

}
