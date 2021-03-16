package Services;

import Dao.CustomersDao;
import Dao.Impl.CustomersDaoImpl;
import Models.Customers;

public class CustomersService {
    private CustomersDao customersDao = new CustomersDaoImpl();
    public void createCustomer(Customers customer) {
        customersDao.create(customer);
    }

    public void deleteCustomer(Customers customer) {
        customersDao.delete(customer);
    }

    public void updateCustomer(Customers customer) {
        customersDao.update(customer);
    }
}
