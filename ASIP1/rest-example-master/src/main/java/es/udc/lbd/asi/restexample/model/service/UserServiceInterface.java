package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import javax.management.InstanceNotFoundException;

import es.udc.lbd.asi.restexample.model.service.dto.UserDTO;

public interface UserServiceInterface {
    public UserDTO findByLogin(String login);
    public List<UserDTO> findAll();
    public UserDTO save(UserDTO user);

}
