package es.udc.lbd.asi.restexample.config;

 import javax.persistence.EntityManagerFactory;

 import org.hibernate.SessionFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.orm.hibernate5.HibernateTransactionManager;
 import org.springframework.transaction.PlatformTransactionManager;
 import org.springframework.transaction.annotation.EnableTransactionManagement;

 @Configuration
 @EnableTransactionManagement
 public class PersistenceConfiguration {

     @Autowired
     private EntityManagerFactory entityManagerFactory;

     @Bean
     public PlatformTransactionManager hibernateTransactionManager() {
         if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
             throw new NullPointerException("factory is not a hibernate factory");
         }
         HibernateTransactionManager transactionManager = new HibernateTransactionManager();
         transactionManager.setSessionFactory(entityManagerFactory.unwrap(SessionFactory.class));
         return transactionManager;
     }
 }