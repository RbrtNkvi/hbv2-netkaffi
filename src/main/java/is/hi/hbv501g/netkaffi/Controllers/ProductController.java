package is.hi.hbv501g.netkaffi.Controllers;

import is.hi.hbv501g.netkaffi.Persistence.Entities.Product;
import is.hi.hbv501g.netkaffi.Persistence.Entities.User;
import is.hi.hbv501g.netkaffi.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {
    ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /**
     * Fetching the products for use in products.html
     *
     * @return direction to products.html with products information
     */
    @GetMapping(value="/products")
    public List<Product> productsGet(){
        return productService.findAll();
    }

    /**
     * Adds a new product to PSQL database
     *
     * @param product new product information
     * @return redirect to products.html
     */
    @PostMapping(value="/addproduct",consumes = "application/json", produces = "application/json")
    public Product addProductPost(@RequestBody Product product){
        return productService.save(product);
    }

    /**
     * Product gets set to deleted, so it can no longer be booked
     *
     * @param name product name
     * @return redirect to products.html
     */
    @PostMapping(value="/delete/{name}", consumes = "application/json", produces = "application/json")
    public Product productDelete(@PathVariable("name") String name){
        try {
            Product productToDelete = productService.findByName(name);
            productService.edit(productToDelete);
            return productToDelete;
        } catch(Exception e) {
            return null;
        }
    }
}
