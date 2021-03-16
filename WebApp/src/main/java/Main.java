import Services.AdminService;

import Models.Admin;

public class Main {
    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        Admin new_admin = new Admin("Masha1", "akula");
        adminService.createAdmin(new_admin);
    }
}
