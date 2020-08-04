package config;

import app.PrepareData;
import app.SessionFactoryClass;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import repositories.ProductRep;
import repositories.UserRep;
import services.ProductService;
import services.UserService;


@Configuration
public class AppConfig {


    @Bean("factory")
    public SessionFactoryClass sessionFactory() {
        return new SessionFactoryClass();
    }

    @Bean("userService")
    public UserService userService() {
        return new UserService();
    }

    @Bean("userRep")
    public UserRep userRep() {
        return new UserRep();
    }

    @Bean("productService")
    public ProductService productService() {
        return new ProductService();
    }

    @Bean("productRep")
     public ProductRep productRep(){
        return new ProductRep();
    }

    @Bean("initial")
    public PrepareData prepareData() {
        return new PrepareData();
    }


}
