package Services;

import Models.Admin;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminServiceTest {

    @Test
    public void testCreateAdmin() {
        AdminService adminService = new AdminService();
        Admin new_admin = new Admin("AdminForTestCreate", "akula");
        adminService.createAdmin(new_admin);

        Admin check_admin = adminService.readAdminByID(new_admin.getAdmin_id());

        Assert.assertEquals(new_admin.getAdmin_login(), check_admin.getAdmin_login());
        Assert.assertEquals(new_admin.getAdmin_password(), check_admin.getAdmin_password());

        adminService.deleteAdmin(new_admin);
    }

    @Test
    public void testDeleteAdmin() {
        AdminService adminService = new AdminService();
        Admin new_admin = new Admin("AdminForTestDelete", "akula");
        adminService.createAdmin(new_admin);
        Admin check_admin = adminService.readAdminByID(new_admin.getAdmin_id());
        Assert.assertEquals(new_admin.getAdmin_login(), check_admin.getAdmin_login());
        Assert.assertEquals(new_admin.getAdmin_password(), check_admin.getAdmin_password());

        adminService.deleteAdmin(new_admin);
        check_admin = adminService.readAdminByID(new_admin.getAdmin_id());
        Assert.assertNull(check_admin);
    }

    @Test
    public void testUpdateAdmin() {
        AdminService adminService = new AdminService();
        Admin new_admin = new Admin("AdminForTestUpdate", "akula");
        adminService.createAdmin(new_admin);
        Admin check_admin = adminService.readAdminByID(new_admin.getAdmin_id());
        Assert.assertEquals(new_admin.getAdmin_login(), check_admin.getAdmin_login());
        Assert.assertEquals(new_admin.getAdmin_password(), check_admin.getAdmin_password());

        new_admin.setAdmin_password("ikea");
        adminService.updateAdmin(new_admin);
        check_admin = adminService.readAdminByID(new_admin.getAdmin_id());
        Assert.assertEquals(new_admin, check_admin);

        adminService.deleteAdmin(new_admin);
    }

    @Test
    public void testReadAdminByID() {
        AdminService adminService = new AdminService();
        Admin new_admin = new Admin("AdminForTestReadAdminByID", "akula");
        adminService.createAdmin(new_admin);
        Admin check_admin = adminService.readAdminByID(new_admin.getAdmin_id());
        Assert.assertEquals(new_admin.getAdmin_id(), check_admin.getAdmin_id());
        adminService.deleteAdmin(new_admin);
    }

    @Test
    public void testUpdateAdminPassword() {
        String newPassword = "ikea";
        AdminService adminService = new AdminService();
        Admin new_admin = new Admin("AdminForTestReadAdminByID", "akula");
        adminService.createAdmin(new_admin);
        adminService.updateAdminPassword(new_admin, newPassword);

        Admin check_admin = adminService.readAdminByID(new_admin.getAdmin_id());
        Assert.assertEquals(new_admin, check_admin);
        adminService.deleteAdmin(new_admin);
    }

    @Test
    public void testReadAdminByLogin() {
        AdminService adminService = new AdminService();
        Admin new_admin = new Admin("AdminForTestReadAdminByLogin", "akula");
        adminService.createAdmin(new_admin);
        Admin check_admin = adminService.readAdminByLogin("AdminForTestReadAdminByLogin");

        Assert.assertEquals("AdminForTestReadAdminByLogin", check_admin.getAdmin_login());
        adminService.deleteAdmin(new_admin);
    }
}