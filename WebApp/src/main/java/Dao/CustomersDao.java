package Dao;
import Models.Customers;

public interface CustomersDao {
    public void create(Customers customer);
    public void update(Customers customer);
    public void delete(Customers customer);
}
