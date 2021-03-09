package orders.store;

import orders.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.GregorianCalendar;
import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    @Query("FROM Customer WHERE NOT id IN (SELECT Ord.customer.id FROM Order AS Ord WHERE Ord.date < :filterDate)")
    List<Customer> customersWithoutOrders(@Param("filterDate") GregorianCalendar date);

    @Query("SELECT O.customer, COUNT(DISTINCT O.id) FROM Order AS O WHERE O.date BETWEEN :startDate AND :endDate")
    List<Customer> numberOfOrdersInYear(@Param("startDate") GregorianCalendar startDate, @Param("endDate") GregorianCalendar endDate);
}
