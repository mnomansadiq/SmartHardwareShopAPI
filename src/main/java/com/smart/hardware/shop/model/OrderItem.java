package com.smart.hardware.shop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Entity
public class OrderItem implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    @Column(name = "local_date", columnDefinition = "DATE")
    private Date orderDate;

    public OrderItem() {

    }

    public OrderItem(User user, List<Product> products, Date localDate) {
        this.user = user;
        this.products = products;
        this.orderDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date localDate) {
        this.orderDate = localDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) &&
                Objects.equals(user, orderItem.user) &&
                Objects.equals(products, orderItem.products) &&
                Objects.equals(orderDate, orderItem.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, products, orderDate);
    }
}
