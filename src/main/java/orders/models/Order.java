package orders.models;

import lombok.Data;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private GregorianCalendar date;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;
}
