package Services;

import Dao.Impl.OrdersDaoImpl;
import Dao.OrdersDao;
import Models.Books;
import Models.Customers;
import Models.Orders;

import java.util.List;

public class OrdersService {
    private OrdersDao ordersDao = new OrdersDaoImpl();
    public void createOrder(Orders order) {
        ordersDao.create(order);
    }

    public List<Orders> readOrdersByCustomerId(Customers customer) {
        return ordersDao.readOrdersById(customer);
    }

    public List<Orders> readOrders() {
        return ordersDao.readOrders();
    }

    public void deleteOrder(Orders order) {
        ordersDao.delete(order);
    }

    public void updateOrder(Orders order) {
        ordersDao.update(order);
    }

    public Orders readOrderByID(int id) {
        return ordersDao.readByID(id);
    }
}
