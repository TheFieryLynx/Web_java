package Dao;
import Models.Orders;

public interface OrdersDao {
    public void create(Orders order);
    public void update(Orders order);
    public void delete(Orders order);
}
