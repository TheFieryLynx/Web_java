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
}
