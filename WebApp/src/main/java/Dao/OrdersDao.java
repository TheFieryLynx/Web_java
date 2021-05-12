package Dao;
import Models.Customers;
import Models.Orders;

import java.util.List;

public interface OrdersDao {
    void create(Orders order);
    void update(Orders order);
    void delete(Orders order);
    Orders readByID(int id);
    List<Orders> readOrdersById(Customers customer);
    List<Orders> readOrders();
}
