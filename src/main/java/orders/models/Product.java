package orders.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String photo;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;
}
