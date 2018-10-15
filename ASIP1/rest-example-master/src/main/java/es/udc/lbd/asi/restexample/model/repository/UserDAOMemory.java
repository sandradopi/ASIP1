package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.User;

@Repository
public class UserDAOMemory implements UserDAO {

    @Override
    public List<User> findAll() {
        return InMemoryDB.users.values().stream().collect(Collectors.toList());
    }
}
