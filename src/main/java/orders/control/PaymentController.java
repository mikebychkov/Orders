package orders.control;

import orders.control.responses.PaymentResponse;
import orders.models.Payment;
import orders.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/details")
    public Payment getDetails(@RequestParam("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping("/add")
    public PaymentResponse addPayment(@RequestParam("inv_id") Integer invId, @RequestParam("amount") Double amount) {
        String status = "FAILED";
        Payment savedPayment = null;
        String info = "";
        try {
            status = "SUCCESS";
            savedPayment = service.addPayment(invId, amount);
        } catch (Exception e) {
            info = e.getLocalizedMessage();
            e.printStackTrace();
        }
        return PaymentResponse.of(status, savedPayment, info);
    }
}
