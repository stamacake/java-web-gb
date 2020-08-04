package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component("initial")
public class PrepareData {

    @Autowired
    private SessionFactoryClass factory;


    @PostConstruct
    public void forcePrepareData() {
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("Hw5/src/main/java/initDB.sql")).collect(Collectors.joining(" "));
            session = factory.getSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
