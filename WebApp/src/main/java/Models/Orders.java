package Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {

    public Orders() {}

    public Orders(String order_list, int order_id, Customers customer_id, String address, Date delivery_time, String status, Date order_time, double order_price) {
        this.order_list = order_list;
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.address = address;
        this.delivery_time = delivery_time;
        this.status = status;
        this.order_time = order_time;
        this.order_price = order_price;
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

    public Date getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(Date delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public String getOrder_list() {
        return order_list;
    }

    public void setOrder_list(String order_list) {
        this.order_list = order_list;
    }

    private String order_list;
    private int order_id;

    //public Customers getCustomer_id() {
//        return customer_id;
//    }

    public void setCustomer_id(Customers customer_id) {
        this.customer_id = customer_id;
    }

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
    private Customers customer_id;

    private String address;
    private Date delivery_time;
    private String status;
    private Date order_time;
    private double order_price;
}
