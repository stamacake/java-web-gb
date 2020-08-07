package repositories;

import app.SessionFactoryClass;
import entities.Product;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("productRep")
public class ProductRep {
    @Autowired
    private SessionFactoryClass sessionFactory;

    public Product findProduct(Long id){
        Session session = sessionFactory.getSession();
        session.beginTransaction();
        Product product = session.find(Product.class, id);
        session.getTransaction().commit();
        return product;
    }

    public void saveProduct(Product product){
        Session session = sessionFactory.getSession();
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
        session.close();
    }

    public List<Product> getAllProducts(){
        Session session = sessionFactory.getSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product", Product.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return products;

    }
}
