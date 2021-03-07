package orders.control.responses;

import lombok.Data;

@Data
public class OrderResponse {

    private String status;
    private Integer id;
    private String info;

    public static OrderResponse of(String status, Integer id, String info) {
        OrderResponse or = new OrderResponse();
        or.setStatus(status);
        or.setId(id);
        or.setInfo(info);
        return or;
    }
}
