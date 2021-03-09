package orders.store;

import orders.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT OD.product, OD.order, AVG(OD.quantity) FROM OrderDetails AS OD GROUP BY OD.product, OD.order HAVING AVG(OD.quantity) > 8")
    List<Product> bulkProducts();

    @Query("SELECT OD.product, COUNT(DISTINCT OD.order) FROM OrderDetails AS OD GROUP BY OD.product HAVING COUNT(DISTINCT OD.order) > 10")
    List<Product> highDemandProducts();
}
