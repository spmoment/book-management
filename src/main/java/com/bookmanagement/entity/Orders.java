package com.bookmanagement.entity;

import com.bookmanagement.enums.EnumStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "order_date_execution")
    private LocalDateTime orderDateExecution;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getOrderDateExecution() {
        return orderDateExecution;
    }

    public void setOrderDateExecution(LocalDateTime orderDateExecution) {
        this.orderDateExecution = orderDateExecution;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                Objects.equals(dateCreated, orders.dateCreated) &&
                Objects.equals(orderDateExecution, orders.orderDateExecution) &&
                status == orders.status &&
                Objects.equals(users, orders.users) &&
                Objects.equals(orderItems, orders.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated, orderDateExecution, status, users, orderItems);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", orderDateExecution=" + orderDateExecution +
                ", status=" + status +
                '}';
    }
}
