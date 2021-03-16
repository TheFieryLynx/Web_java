package Dao;
import Models.Admin;

public interface AdminDao {
    public void create(Admin admin);
    public void update(Admin admin);
    public void delete(Admin admin);
}
