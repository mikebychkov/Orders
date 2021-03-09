package orders.control.responses;

import lombok.Data;

@Data
public class OrderResponse {

    private String status;
    private Integer invoiceId;
    private String info;

    public static OrderResponse of(String status, Integer invoiceId, String info) {
        OrderResponse or = new OrderResponse();
        or.setStatus(status);
        or.setInvoiceId(invoiceId);
        or.setInfo(info);
        return or;
    }
}
