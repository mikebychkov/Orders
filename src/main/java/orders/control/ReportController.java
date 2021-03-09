package orders.control;

import orders.models.*;
import orders.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/expired_invoices")
    public List<Invoice> expiredInvoices() {
        return service.expiredInvoices();
    }

    @GetMapping("/wrong_date_invoices")
    public List<Invoice> wrongDateInvoices() {
        return service.wrongDateInvoices();
    }

    @GetMapping("/orders_without_details")
    public List<Order> ordersWithoutDetails(@RequestParam("date") GregorianCalendar date) {
        return service.ordersWithoutDetails(date);
    }

    @GetMapping("/customers_without_orders")
    public List<Customer> customersWithoutOrders(@RequestParam("year") Short year) {
        return service.customersWithoutOrders(year);
    }

    @GetMapping("/customers_last_orders")
    public List<Order> customersLastOrders() {
        return service.customersLastOrders();
    }

    @GetMapping("/overpaid_invoices")
    public List<Invoice> overpaidInvoices() {
        return service.overpaidInvoices();
    }

    @GetMapping("/orders_without_invoices")
    public List<Order> ordersWithoutInvoices() {
        return service.ordersWithoutInvoices();
    }

    @GetMapping("/high_demand_products")
    public List<Product> highDemandProducts() {
        return service.highDemandProducts();
    }

    @GetMapping("/bulk_products")
    public List<Product> bulkProducts() {
        return service.bulkProducts();
    }

    @GetMapping("/number_of_products_in_year")
    public List<Customer> numberOfProductsInYear(@RequestParam("year") Short year) {
        return service.numberOfOrdersInYear(year);
    }
}
