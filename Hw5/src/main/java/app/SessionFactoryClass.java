package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("factory")
public class SessionFactoryClass {
    private SessionFactory factory;

    @PostConstruct
    public void init(){
      factory   = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        System.out.println("init factory");
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

}
