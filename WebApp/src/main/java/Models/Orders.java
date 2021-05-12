package Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders {

    public Orders() {}

    public Orders(Customers customer_id, String address, java.sql.Date delivery_time, String status, java.sql.Date order_time, double order_price,  Books book_id) {
        this.customer_id = customer_id;
        this.address = address;
        this.delivery_time = delivery_time;
        this.status = status;
        this.order_time = order_time;
        this.order_price = order_price;
        this.book_id = book_id;
    }

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.sql.Date getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(java.sql.Date delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(java.sql.Date order_time) {
        this.order_time = order_time;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    @ManyToOne(targetEntity=Books.class)
    @JoinColumn(name = "book_id")
    public Books getBook_id() {
        return book_id;
    }

    public void setBook_id(Books book_id) {
        this.book_id = book_id;
    }

    private Books book_id;
    private int order_id;

    @ManyToOne(targetEntity=Customers.class)
    @JoinColumn(name = "customer_id")
    public Customers getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customers customer_id) {
        this.customer_id = customer_id;
    }

    private Customers customer_id;

    private String address;
    private java.sql.Date delivery_time;
    private String status;
    private java.sql.Date order_time;
    private double order_price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return order_id == orders.order_id &&
                Double.compare(orders.order_price, order_price) == 0 &&
                book_id.equals(orders.book_id) &&
                customer_id.equals(orders.customer_id) &&
                address.equals(orders.address) &&
                delivery_time.equals(orders.delivery_time) &&
                status.equals(orders.status) &&
                order_time.equals(orders.order_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, order_id, customer_id, address, delivery_time, status, order_time, order_price);
    }
}
