package task2;

import task2.core.Buyer;
import task2.core.Product;
import task2.core.Purchases;

import javax.persistence.EntityManager;
import java.util.List;

public class FinalEntity {
    private final EntityManager entityManager;

    public FinalEntity(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void addProduct(Product product){
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    public void addPurchase(Purchases purchases){
        entityManager.getTransaction().begin();
        entityManager.persist(purchases);
        entityManager.getTransaction().commit();
    }

    public void addBuyer(Buyer buyer){
        entityManager.getTransaction().begin();
        entityManager.persist(buyer);
        entityManager.getTransaction().commit();
    }

    public List<Product> showProductsByConsumer(String name){
        return entityManager.createQuery(
                "SELECT product FROM Purchases p WHERE p.buyer.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Buyer> showConsumersByProductTitle(String name){
        return entityManager.createQuery("select buyer from Purchases p where p.product.name = :name", Buyer.class)
                .setParameter("name", name)
                .getResultList();
    }

    public int deleteConsumer(String name){
        entityManager.getTransaction().begin();
        int success = entityManager.createQuery(
                "DELETE FROM Buyer WHERE name = :name")
                .setParameter("name",name)
                .executeUpdate();
        entityManager.getTransaction().commit();
        return success;
    }

    public int deleteProduct(String name){
        entityManager.getTransaction().begin();
        int success = entityManager.createQuery(
                "DELETE FROM Product WHERE name = :name")
                .setParameter("name",name)
                .executeUpdate();
        entityManager.getTransaction().commit();
        return success;
    }

    public void buy(Integer buyerId, Integer productId){
        entityManager.getTransaction().begin();
        Buyer buyer = entityManager.find(Buyer.class, buyerId);
        Product product = entityManager.find(Product.class, productId);
        if(buyer ==null) System.out.println("Отсутствует покупатель");
        else if (product == null) System.out.println("Отсутствует товар");
        else {
            entityManager.persist(new Purchases(buyer, product, product.getPrice()));
            entityManager.getTransaction().commit();
        }
    }

}
