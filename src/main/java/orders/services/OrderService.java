package orders.services;

import orders.models.*;
import orders.store.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repo.findById(id).get();
    }

    public Order addOrder(Integer custId, Integer prodId, Short quantity) {

        ServiceHelper.checkValue(quantity, "Quantity");

        Customer customer = customerService.getById(custId);
        Product product = productService.getById(prodId);

        // ORDER
        Order order = new Order();
        order.setDate(ServiceHelper.getRecentDate());
        order.setCustomer(customer);

        // DETAILS
        order.setDetails(List.of(detailsService.addOrderDetails(order, product, quantity)));

        Order savedOrder = repo.save(order);

        // INVOICE
        invoiceService.addInvoice(savedOrder);

        return savedOrder;
    }
}
