package com.stamacake.mvc.Constroller;

import com.stamacake.mvc.entities.Product;
import com.stamacake.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("prods", productService.getAllProducts());
        return "allproducts";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String name, @RequestParam int price) {
        productService.saveProduct(new Product(name, price));
        return "redirect:/products/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return "redirect:/products/all";
    }

    @GetMapping("/find")
    @ResponseBody
    public Product findProduct(@RequestParam long id) {
        return productService.findProduct(id);
    }
}
