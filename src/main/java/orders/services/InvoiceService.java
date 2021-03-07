package orders.services;

import orders.models.Invoice;
import orders.models.Order;
import orders.store.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo repo;

    public Invoice getById(Integer id) {
        Optional<Invoice> op = ServiceHelper.checkId(id, repo, "Invoice");
        return op.get();
    }

    public Invoice addInvoice(Order order) {

        Double total = order.getDetails().stream().map(d -> d.getProduct().getPrice() * d.getQuantity()).reduce((a, b) -> a + b).get();

        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setAmount(total);
        invoice.setIssued(order.getDate());
        invoice.setDue(getDue(order));

        return repo.save(invoice);
    }

    private Calendar getDue(Order order) {
        Calendar due = order.getDate();
        due.add(Calendar.DATE, 7);
        return due;
    }
}
