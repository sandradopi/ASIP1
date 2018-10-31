package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.User_;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;

@Repository
public class UserDAOHibernate extends GenericDAOHibernate implements UserDAO {


	@Override
	public User_ findByLogin(String login){
		return (User_) getSession().createQuery("from User_ p where p.login = :login").setParameter("login",login).uniqueResult();
	}
	
	@Override
	public void save(User_ user) {
		getSession().saveOrUpdate(user);
	}
	
	@Override
	public List<User_> findAll() {
		return getSession().createQuery("from User_").list();
	}
	
	@Override
	public User_ findById(Long idUser){
		return (User_) getSession().createQuery("from User_ p where p.idUser = :idUser").setParameter("idUser", idUser).uniqueResult();
	}
	
	@Override
	public NormalUser findByIdNormal(Long idUser){
		return ( NormalUser) getSession().createQuery("from User_ p where p.idUser = :idUser AND p.authority=\"USER\"").setParameter("idUser", idUser).uniqueResult();
	}



}
