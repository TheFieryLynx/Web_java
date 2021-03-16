package Services;

import Dao.Impl.OrdersDaoImpl;
import Dao.OrdersDao;
import Models.Orders;

public class OrdersService {
    private OrdersDao ordersDao = new OrdersDaoImpl();
    public void createOrder(Orders order) {
        ordersDao.create(order);
    }

    public void deleteOrder(Orders order) {
        ordersDao.delete(order);
    }

    public void updateOrder(Orders order) {
        ordersDao.update(order);
    }
}
