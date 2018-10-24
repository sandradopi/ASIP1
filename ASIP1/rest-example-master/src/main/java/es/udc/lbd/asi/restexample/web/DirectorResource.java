package es.udc.lbd.asi.restexample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.udc.lbd.asi.restexample.model.service.DirectorService;
import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;

@RequestMapping("/api/directors")
public class DirectorResource {
	@Autowired
    private DirectorService directorService;
	
	@PostMapping
	public DirectorDTO save(@RequestBody DirectorDTO director) {
		return directorService.save(director);
	}
}
