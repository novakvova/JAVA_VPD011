package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_baskets")
@IdClass(BaketPK.class)
public class Basket {
    @Id
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;
    @Id
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    private int count;
}
