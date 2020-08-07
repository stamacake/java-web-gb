import app.PrepareData;
import app.SessionFactoryClass;
import config.AppConfig;
import entities.Product;
import entities.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.UserRep;
import services.ProductService;
import services.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        ProductService productService = context.getBean("productService", ProductService.class);

        //load user
        User user1 = userService.findUser(1l);
        System.out.println(user1.getName()+" "+user1.getAge()); // check
        //save user
        userService.saveUser(new User("newUser",34)); // check
        System.out.println(userService.findUser(4l).getName()); //check last save with find
        //get all users
        List<User> users = userService.getAllUsers();
        System.out.println(users); // check

        //load product
        Product product1 = productService.findProduct(1l);
        System.out.println(product1.getName()+" "+product1.getName()); // check
        //save product
        productService.saveProduct(new Product("newProduct",888)); // check
        System.out.println(productService.findProduct(4l).getName()); //check last save with find
        //get all products
        List<Product> products = productService.getAllProducts();
        System.out.println(products); // check

    }
}
