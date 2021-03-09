package orders.control;

import orders.control.responses.PaymentResponse;
import orders.models.Payment;
import orders.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/details")
    public ResponseEntity<Payment> getDetails(@RequestParam("id") Integer id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public PaymentResponse addPayment(@RequestParam("inv_id") Integer invId, @RequestParam("amount") Double amount) {
        String status = "";
        Payment savedPayment = null;
        String info = "";
        try {
            status = "SUCCESS";
            savedPayment = service.addPayment(invId, amount);
        } catch (Exception e) {
            status = "FAILED";
            info = e.getLocalizedMessage();
            e.printStackTrace();
        }
        return PaymentResponse.of(status, savedPayment, info);
    }
}
