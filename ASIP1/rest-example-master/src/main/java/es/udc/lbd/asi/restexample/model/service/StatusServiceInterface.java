package es.udc.lbd.asi.restexample.model.service;


import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;


public interface StatusServiceInterface {
	public void save(Long MovieId, String statu);
	public StatusDTO update(Long idMovie, Integer valoracion);
	public void deleteByIdMovieUser(Long idMovie);
	public StatusDTO findByMovieUser(Long idMovie);
	public Long countVistas(String login);
	public Long countVistasValoration(String login);
	public Long countPendientes(String login);
	
}
