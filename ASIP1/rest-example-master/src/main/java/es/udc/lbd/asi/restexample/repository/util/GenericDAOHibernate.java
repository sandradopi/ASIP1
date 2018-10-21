package es.udc.lbd.asi.restexample.repository.util;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericDAOHibernate {
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        /*
         * Esta forma de obtener el SessionFactory es lo que se conoce como ñapa, pero
         * os sirve para montar los DAOs como en ID y no liaros haciendo lo mismo de dos
         * formas diferentes
         */
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
