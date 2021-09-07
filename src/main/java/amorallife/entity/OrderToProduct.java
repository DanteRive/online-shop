package amorallife.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders_to_products")
public class OrderToProduct extends AbstractEntity{

    private Order order;
    private Product product;

    @Id
    @SequenceGenerator(name="order_products_s",sequenceName="order_products_s")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="order_products_s")
    @Column(nullable = false, name = "id")
    public Long getId() {
        return super.getId();
    }

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
