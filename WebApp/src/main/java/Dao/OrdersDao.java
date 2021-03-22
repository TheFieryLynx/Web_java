package Dao;
import Models.Orders;

public interface OrdersDao {
    void create(Orders order);
    void update(Orders order);
    void delete(Orders order);
    Orders readByID(int id);
}
