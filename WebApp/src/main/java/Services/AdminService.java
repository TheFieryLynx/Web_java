package Services;

import Dao.AdminDao;
import Dao.Impl.AdminDaoImpl;
import Models.Admin;

public class AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    public void createAdmin(Admin admin) {
        adminDao.create(admin);
    }

    public void deleteAdmin(Admin admin) {
        adminDao.delete(admin);
    }

    public void updateAdmin(Admin admin) {
        adminDao.update(admin);
    }

    public Admin readAdminByID(int id) {
        return adminDao.readByID(id);
    }

    public Admin readAdminByLogin(String login) {
        return adminDao.readByLogin(login);
    }

    public void updateAdminPassword(Admin admin, String password) {
        admin.setAdmin_password(password);
        adminDao.update(admin);
    }

}
