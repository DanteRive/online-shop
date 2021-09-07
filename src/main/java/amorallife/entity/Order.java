package amorallife.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order extends AbstractEntity{

    private User user;
    private Double generalPrice;
    private List<Product> products;

    @Id
    @SequenceGenerator(name="orders_s",sequenceName="orders_s")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="orders_s")
    @Column(nullable = false, name = "id")
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "general_price", nullable = false)
    public Double getGeneralPrice() {
        return generalPrice;
    }

    public void setGeneralPrice(Double generalPrice) {
        this.generalPrice = generalPrice;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_to_products",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
