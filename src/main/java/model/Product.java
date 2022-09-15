package model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "sku_code")
    private String skuCode;
    @Column(name = "unit_price")
    private float unitPrice;

    public Product() {
    }

    public Product(String name, String skuCode, float unitPrice) {
        this.name = name;
        this.skuCode = skuCode;
        this.unitPrice = unitPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
