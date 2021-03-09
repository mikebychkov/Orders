package orders.store;

import orders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.GregorianCalendar;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query("FROM Order WHERE Order.date < :filterDate AND NOT id IN (SELECT d.order.id FROM OrderDetails d)")
    List<Order> ordersWithoutDetails(@Param("filterDate") GregorianCalendar date);

    @Query("FROM Order AS Ord LEFT JOIN "
            + " (SELECT O.id, MAX(O.date) AS date FROM Order AS O GROUP BY O.id) AS Tmp "
            + " WHERE Ord.id = Tmp.id AND Ord.date = Tmp.date")
    List<Order> customersLastOrders();

    @Query("FROM Order WHERE NOT id IN (SELECT D.order.id AS id FROM OrderDetails AS D UNION SELECT I.order.id FROM Invoice AS I)")
    List<Order> ordersWithoutInvoices();
}
