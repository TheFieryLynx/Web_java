package Services;

import Dao.CustomersDao;
import Dao.Impl.CustomersDaoImpl;
import Models.Admin;
import Models.Customers;

public class CustomersService {
    private CustomersDao customersDao = new CustomersDaoImpl();
    public void createCustomer(Customers customer) {
        customersDao.create(customer);
    }

    public void deleteCustomerForever(Customers customer) {
        customersDao.delete(customer);
    }

    public void updateCustomer(Customers customer) {
        customersDao.update(customer);
    }

    public Customers readCustomerByID(int id) {
        return customersDao.readByID(id);
    }

    public Customers readCustomerByLogin(String login) {
        return customersDao.readByLogin(login);
    }

    public void deleteCustomerAccount(Customers customer) {
        customersDao.deleteAccount(customer);
    }

}
