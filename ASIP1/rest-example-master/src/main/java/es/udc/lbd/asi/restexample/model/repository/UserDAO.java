package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.User;

public interface UserDAO {

    List<User> findAll();
}
