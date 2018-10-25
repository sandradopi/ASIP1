package es.udc.lbd.asi.restexample.web;

import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.service.GenreService;
import es.udc.lbd.asi.restexample.model.service.dto.GenreDTO;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;



@RestController
@RequestMapping("/api/genres")
public class GenreResource {

    @Autowired
    private GenreService genreService;


    @PostMapping
    public GenreDTO save(@RequestBody @Valid GenreDTO genre, Errors errors) throws RequestBodyNotValidException {
        errorHandler(errors); 
        return genreService.save(genre);
    }
    
    private void errorHandler(Errors errors) throws RequestBodyNotValidException {
        if (errors.hasErrors()) {
            String errorMsg = errors.getFieldErrors().stream()
                    .map(fe -> String.format("%s.%s %s", fe.getObjectName(), fe.getField(), fe.getDefaultMessage()))
                    .collect(Collectors.joining("; "));
            throw new RequestBodyNotValidException(errorMsg);
        }
    }
    
}
