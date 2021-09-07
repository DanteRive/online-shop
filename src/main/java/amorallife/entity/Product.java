package amorallife.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

    private String name;
    private Double price;
    private ProductType productType;

    @Id
    @SequenceGenerator(name="products_s",sequenceName="products_s")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="products_s")
    @Column(nullable = false, name = "id")
    public Long getId() {
        return super.getId();
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    public ProductType getType() {
        return productType;
    }

    public void setType(ProductType productType) {
        this.productType = productType;
    }
}
