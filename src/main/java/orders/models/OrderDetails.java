package orders.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ord_id")
    private Order order;

    private Short quantity;
}
