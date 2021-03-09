package orders.services;

import orders.models.Product;
import orders.store.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(Integer id) throws Exception {
        Optional<Product> op = ServiceHelper.checkId(id, repo, "Product");
        return op.get();
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }
}
