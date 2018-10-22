package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import javax.management.InstanceNotFoundException;

import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;

public interface MovieServiceInterface {
    public List<MovieDTO> findAll();
    public MovieDTO findById(Long id) throws InstanceNotFoundException;;
    public MovieDTO save(MovieDTO movie);
    public void deleteById(Long idMovie);
    public MovieDTO update(MovieDTO post);
}
