package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "order_date")
    private String orderDate;

    @OneToOne
    private Customer customer;

    @OneToMany(targetEntity = OrderLine.class)
    private Set<OrderLine> orderLine;



    public Order() {
    }

    public Order(long id, String orderDate) {
        this.id = id;
        this.orderDate = orderDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(Set<OrderLine> orderLine) {
        this.orderLine = orderLine;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate='" + orderDate + '\'' +
                ", customer=" + customer +
                ", orderLine=" + orderLine +
                '}';
    }
}
