package orders.services;

import orders.models.Order;
import orders.models.OrderDetails;
import orders.models.Product;
import orders.store.OrderDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepo repo;

    public OrderDetails addOrderDetails(Order order, Product product, Short quantity) {
        OrderDetails od = new OrderDetails();
        od.setOrder(order);
        od.setProduct(product);
        od.setQuantity(quantity);
        return repo.save(od);
    }
}
