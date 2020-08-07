package services;

import entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositories.ProductRep;

import java.util.List;

@Component("productService")
public class ProductService {

    @Autowired
    private ProductRep productRep;

    public Product findProduct(Long id){
        return productRep.findProduct(id);
    }

    public void saveProduct(Product product){
        productRep.saveProduct(product);
    }

    public List<Product> getAllProducts(){
        return productRep.getAllProducts();
    }

}
