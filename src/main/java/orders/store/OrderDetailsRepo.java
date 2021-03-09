package orders.store;

import orders.models.Order;
import orders.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> getAllByOrOrder(Order order);
}
