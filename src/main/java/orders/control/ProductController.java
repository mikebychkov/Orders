package orders.control;

import orders.models.Product;
import orders.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/list")
    public List<Product> getList() {
        return service.getAll();
    }

    @GetMapping("/details")
    public Product getDetails(@RequestParam("id") Integer id) {
        return service.getById(id);
    }
}
