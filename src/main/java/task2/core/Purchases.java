package task2.core;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "buyerid")
    private Buyer buyer;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "prodictid")
    private Product product;

    @Column(name = "price")
    private Integer price;

    @Override
    public String toString(){
        return "id: "+id+", Покупатель: "+buyer+", Товар: "+product+",  Цена: "+price;
    }

    public Purchases() {
    }

    public Purchases(Buyer buyer, Product product, Integer price) {
        this.buyer = buyer;
        this.product = product;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
