package hw3;

import hw3.Entities.Lot;
import hw3.Entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {


    protected static long checkSum(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        Criteria criteria = session.createCriteria(Lot.class);
        String sum = "select sum(current_bet) from lot";
        criteria.setProjection(Projections.sum("current_bet"));
        List list = criteria.list();
        session.getTransaction().commit();
        return (long) list.get(0);
    }

    protected static void pessimistic(SessionFactory sessionFactory) throws InterruptedException {
        PrepareDataApp.forcePrepareData();
        Session session = null;
        // threads users
        List<Callable<Void>> users = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            // get user
            User user = session.find(User.class, i);
            session.getTransaction().commit();
            session.close();
            Random random = new Random();

            // user tasks
            Callable<Void> task = () -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        Session session1 = sessionFactory.openSession();
                        session1.beginTransaction();
                        Lot lot = session1.createQuery("from Lot l where l.id = :id", Lot.class)
                                .setParameter("id", (long) random.nextInt(4) + 1)
                                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                                .getSingleResult();
                        lot.setCurrent_bet(lot.getCurrent_bet() + 100);
                        lot.setUser(user);
                        session1.save(lot);
                        session1.getTransaction().commit();
                        Thread.sleep(1);
                    }
                } catch (Exception e) {
                    System.out.println("error");
                    System.out.println(e.getMessage());
                }

                return null;
            };
            users.add(task);
        }
        ExecutorService executor = (ExecutorService) Executors.newFixedThreadPool(8);
        long start = System.currentTimeMillis();
        executor.invokeAll(users);
        executor.shutdown();
        System.out.println(System.currentTimeMillis() - start + "ms PESSIMISTIC");


        //check sum
        System.out.println("Final sum: " + checkSum(sessionFactory));
    }

    protected static void optimistic(SessionFactory sessionFactory) throws InterruptedException {

        PrepareDataApp.forcePrepareData();
        Session session = null;
        // threads users
        List<Callable<Void>> users = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            // get user
            User user = session.find(User.class, i);
            session.getTransaction().commit();
            session.close();
            Random random = new Random();

            // user tasks
            Callable<Void> task = () -> {
                for (int j = 0; j < 1000; j++) {

                    Session session1 = sessionFactory.openSession();
                    session1.beginTransaction();
                    Lot lot = session1.createQuery("from Lot l where l.id = :id", Lot.class)
                            .setParameter("id", (long) random.nextInt(4) + 1)
                            .setLockMode(LockModeType.OPTIMISTIC)
                            .getSingleResult();
                    lot.setCurrent_bet(lot.getCurrent_bet() + 100);
                    lot.setUser(user);
                    try {
                        session1.save(lot);
                        session1.getTransaction().commit();
                    } catch (OptimisticLockException e) {
                        //System.out.println("error");
                        session1.getTransaction().rollback();
                        j--;

                    }
                    Thread.sleep(1);
                }


                return null;
            };
            users.add(task);
        }
        ExecutorService executor = (ExecutorService) Executors.newFixedThreadPool(8);
        long start = System.currentTimeMillis();
        executor.invokeAll(users);
        executor.shutdown();
        System.out.println(System.currentTimeMillis() - start + "ms OPTIMISTIC");


        //check sum
        System.out.println("Final sum: " + checkSum(sessionFactory));
    }

    public static void main(String[] args) throws InterruptedException {


        SessionFactory sessionFactory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        pessimistic(sessionFactory);
        optimistic(sessionFactory);
    }
}
