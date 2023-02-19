package models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="tbl_product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500, nullable = false)
    private String name;
    private int priority;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    private boolean isDelete;
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    public ProductImage() {
    }

    public ProductImage(String name, int priority, Date dateCreated, boolean isDelete, Product product) {
        this.name = name;
        this.priority = priority;
        this.dateCreated = dateCreated;
        this.isDelete = isDelete;
        this.product = product;
    }
}
