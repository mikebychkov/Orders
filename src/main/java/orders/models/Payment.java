package orders.models;

import lombok.Data;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private GregorianCalendar date;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "inv_id")
    private Invoice invoice;
}
