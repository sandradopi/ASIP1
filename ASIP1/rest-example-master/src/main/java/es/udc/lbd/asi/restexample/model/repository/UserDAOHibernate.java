package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import javax.management.InstanceNotFoundException;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;

@Repository
public class UserDAOHibernate extends GenericDAOHibernate implements UserDAO {


	@Override
	public User findByLogin(String login){
		return (User) getSession().createQuery("from User p where p.login = :login").setParameter("login",login).uniqueResult();
	}
	
	@Override
	public void save(User user) {
		getSession().saveOrUpdate(user);
	}
	
	@Override
	public List<User> findAll() {
		return getSession().createQuery("from User").list();
	}
	
	@Override
	public User findById(Long idUser){
		return (User) getSession().createQuery("from User p where p.idUser = :idUser").setParameter("idUser", idUser).uniqueResult();
	}

	@Override
	public void deleteById(Long idUser){
		getSession().delete(findById(idUser));
	}

}
