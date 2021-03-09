package orders.services;

import orders.models.*;
import orders.store.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo repo;

    @Autowired
    private OrderDetailsService detailsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceService invoiceService;

    public Order getById(Integer id) {
        Optional<Order> op = ServiceHelper.checkId(id, repo, "Order");
        return repo.findById(id).get();
    }

    public Invoice addOrder(Integer custId, Integer prodId, Short quantity) throws Exception {

        ServiceHelper.checkValue(quantity, "Quantity");

        Customer customer = customerService.getById(custId);
        Product product = productService.getById(prodId);

        // ORDER
        Order order = new Order();
        order.setDate(ServiceHelper.getRecentDate());
        order.setCustomer(customer);

        Order savedOrder = repo.save(order);

        // DETAILS
        OrderDetails savedDetails = detailsService.addOrderDetails(savedOrder, product, quantity);

        // INVOICE
        Invoice savedInvoice = invoiceService.addInvoice(savedOrder, List.of(savedDetails));

        return savedInvoice;
    }
}
