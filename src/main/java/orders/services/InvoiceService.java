package orders.services;

import orders.models.Invoice;
import orders.models.Order;
import orders.models.OrderDetails;
import orders.store.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo repo;

    @Autowired OrderDetailsService orderDetailsService;

    public Invoice getById(Integer id) {
        Optional<Invoice> op = ServiceHelper.checkId(id, repo, "Invoice");
        return op.get();
    }

    public Invoice addInvoice(Order order, List<OrderDetails> details) {

        Double total = details.stream().map(d -> d.getProduct().getPrice() * d.getQuantity()).reduce((a, b) -> a + b).get();

        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setAmount(total);
        invoice.setIssued(order.getDate());
        invoice.setDue(getDue(order));

        return repo.save(invoice);
    }

    private GregorianCalendar getDue(Order order) {
        GregorianCalendar due = new GregorianCalendar();
        due.setTimeInMillis(order.getDate().getTimeInMillis());
        due.add(Calendar.DATE, 7);
        return due;
    }

    public List<Invoice> getList() {
        return repo.findAll();
    }
}
