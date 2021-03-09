package orders.services;

import orders.models.Customer;
import orders.models.Invoice;
import orders.models.Order;
import orders.models.Product;
import orders.store.CustomerRepo;
import orders.store.InvoiceRepo;
import orders.store.OrderRepo;
import orders.store.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<Invoice> expiredInvoices() {
        return invoiceRepo.expiredInvoices();
    }

    public List<Invoice> wrongDateInvoices() {
        return invoiceRepo.wrongDateInvoices();
    }

    public List<Order> ordersWithoutDetails(GregorianCalendar date) {
        return orderRepo.ordersWithoutDetails(date);
    }

    public List<Customer> customersWithoutOrders(Short year) {
        GregorianCalendar date = new GregorianCalendar(year + 1, Calendar.JANUARY, 1);
        return customerRepo.customersWithoutOrders(date);
    }

    public List<Order> customersLastOrders() {
        return orderRepo.customersLastOrders();
    }

    public List<Invoice> overpaidInvoices() {
        return invoiceRepo.overpaidInvoices();
    }

    public List<Order> ordersWithoutInvoices() {
        return orderRepo.ordersWithoutInvoices();
    }

    public List<Product> bulkProducts() {
        return productRepo.bulkProducts();
    }

    public List<Product> highDemandProducts() {
        return productRepo.highDemandProducts();
    }

    public List<Customer> numberOfOrdersInYear(Short year) {
        GregorianCalendar startDate = new GregorianCalendar(year, Calendar.JANUARY, 1);
        GregorianCalendar endDate = new GregorianCalendar(year + 1, Calendar.JANUARY, 1);
        return customerRepo.numberOfOrdersInYear(startDate, endDate);
    }
}
