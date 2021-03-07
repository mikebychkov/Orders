package orders.control;

import orders.control.responses.OrderResponse;
import orders.models.Order;
import orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/details")
    public Order getDetails(@RequestParam("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping("/add")
    public OrderResponse addOrder(@RequestParam("cust_id") Integer custId
                                , @RequestParam("prod_id") Integer prodId
                                , @RequestParam("quantity") Short quantity) {

        String status = "FAILED";
        Integer id = null;
        String info = "";
        try {
            status = "SUCCESS";
            Order savedOrder = service.addOrder(custId, prodId, quantity);
            id = savedOrder.getId();
        } catch (Exception e) {
            info = e.getLocalizedMessage();
            e.printStackTrace();
        }
        return OrderResponse.of(status, id, info);
    }
}
