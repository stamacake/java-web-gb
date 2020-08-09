package com.stamacake.mvc.services;

import com.stamacake.mvc.repositories.ProductRep;
import com.stamacake.mvc.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("productService")
public class ProductService {

    @Autowired
    private ProductRep productRep;

    public void delete(long id) {
        productRep.delete(id);
    }

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
