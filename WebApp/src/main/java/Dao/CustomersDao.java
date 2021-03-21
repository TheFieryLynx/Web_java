package Dao;

import Models.Customers;

public interface CustomersDao {
    void create(Customers customer);
    void update(Customers customer);
    void delete(Customers customer);
    Customers readByID(int id);
    Customers readByLogin(String login);
    void deleteAccount(Customers customer);
}
