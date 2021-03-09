package orders.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;
    private GregorianCalendar issued;
    private GregorianCalendar due;

    @OneToOne
    @JoinColumn(name = "ord_id")
    private Order order;
}
