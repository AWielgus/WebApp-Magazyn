package aw.webappmagazyn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
    private int amount;
    private int price;
    private String description;
    private boolean hidden;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", productTypeID=" + productType +
                ", amount=" + amount +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", hidden=" + hidden +
                '}';
    }
}
