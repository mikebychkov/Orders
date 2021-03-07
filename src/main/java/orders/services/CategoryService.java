package orders.services;

import orders.models.Category;
import orders.models.Product;
import orders.store.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo repo;

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Category getById(Integer id) {
        Optional<Category> op = ServiceHelper.checkId(id, repo, "Category");
        return op.get();
    }
}
