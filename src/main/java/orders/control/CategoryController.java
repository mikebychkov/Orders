package orders.control;

import orders.models.Category;
import orders.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/list")
    public List<Category> getList() {
        return service.getAll();
    }

    @GetMapping("/details")
    public Category getDetails(@RequestParam("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category) {
        return service.addCategory(category);
    }
}
