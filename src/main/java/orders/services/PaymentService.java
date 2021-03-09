package orders.services;

import orders.models.Invoice;
import orders.models.Order;
import orders.models.Payment;
import orders.store.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo repo;

    @Autowired
    private InvoiceService invoiceService;

    public Payment getById(Integer id) {
        Optional<Payment> op = ServiceHelper.checkId(id, repo, "Payment");
        return repo.findById(id).get();
    }

    public Payment addPayment(Integer invId, Double amount) {

        ServiceHelper.checkValue(amount, "Amount");

        Invoice invoice = invoiceService.getById(invId);

        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setDate(ServiceHelper.getRecentDate());
        payment.setAmount(amount);

        return repo.save(payment);
    }
}
