package orders.control;

import orders.control.responses.OrderResponse;
import orders.models.Invoice;
import orders.models.Order;
import orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/details")
    public ResponseEntity<Order> getDetails(@RequestParam("id") Integer id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public OrderResponse addOrder(@RequestParam("cust_id") Integer custId
                                , @RequestParam("prod_id") Integer prodId
                                , @RequestParam("quantity") Short quantity) {

        String status = "";
        Integer id = null;
        String info = "";
        try {
            status = "SUCCESS";
            Invoice invoice = service.addOrder(custId, prodId, quantity);
            id = invoice.getId();
        } catch (Exception e) {
            status = "FAILED";
            info = e.getLocalizedMessage();
            e.printStackTrace();
        }
        return OrderResponse.of(status, id, info);
    }
}
