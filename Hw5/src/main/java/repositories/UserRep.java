package repositories;

import app.SessionFactoryClass;
import entities.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component("userRep")
public class UserRep {

    @Autowired
    private SessionFactoryClass sessionFactory;

    public User findUser(Long id){
        Session session = sessionFactory.getSession();
        session.beginTransaction();
        User user = session.find(User.class, id);
        session.getTransaction().commit();
        return user;
    }

    public void saveUser(User user){
        Session session = sessionFactory.getSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<User> getAllUsers(){
        Session session = sessionFactory.getSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User", User.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return users;

    }
}
