package Dao;
import Models.Admin;

public interface AdminDao {
    void create(Admin admin);
    void update(Admin admin);
    void delete(Admin admin);
    Admin readByID(int id);
    Admin readByLogin(String login);
}
