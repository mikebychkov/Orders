package orders.control.responses;

import lombok.Data;
import orders.models.Payment;

@Data
public class PaymentResponse {

    private String status;
    private Payment payment;
    private String info;

    public static PaymentResponse of(String status, Payment payment, String info) {
        PaymentResponse pr = new PaymentResponse();
        pr.setStatus(status);
        pr.setPayment(payment);
        pr.setInfo(info);
        return pr;
    }
}
