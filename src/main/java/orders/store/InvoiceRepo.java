package orders.store;

import orders.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

    @Query("FROM Invoice AS Inv WHERE Inv.issued > Inv.due")
    List<Invoice> expiredInvoices();

    @Query("SELECT Inv FROM Invoice AS Inv LEFT JOIN Order AS Ord ON Inv.order.id = Ord.id WHERE Inv.issued < Ord.date")
    List<Invoice> wrongDateInvoices();

    @Query("FROM Invoice WHERE id IN ( "
            + " SELECT I.id, MAX(I.amount), SUM(P.amount) "
            + " FROM Invoice AS I LEFT JOIN Payment AS P ON I.id = P.invoice.id "
            + " GROUP BY I.id HAVING SUM(P.amount) > MAX(I.amount)"
            + " )")
    List<Invoice> overpaidInvoices();
}
