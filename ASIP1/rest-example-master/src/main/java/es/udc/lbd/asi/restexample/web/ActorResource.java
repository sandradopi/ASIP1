package es.udc.lbd.asi.restexample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.model.service.ActorService;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;

@RestController
@RequestMapping("/api/actors")
public class ActorResource {
	@Autowired
    private ActorService actorService;
	
	@PostMapping
	public ActorDTO save(@RequestBody ActorDTO actor) {
		return actorService.save(actor);
	}
}
