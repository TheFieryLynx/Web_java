public class Admin
{
    public Admin(String admin_login, String admin_password) {
        this.admin_login = admin_login;
        this.admin_password = admin_password;
    }

    public String getAdmin_login() {
        return admin_login;
    }

    public void setAdmin_login(String admin_login) {
        this.admin_login = admin_login;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    private String admin_login;
    private String admin_password;
}

//CREATE TABLE IF NOT EXISTS Admin (
//	Admin_login varchar(100) PRIMARY KEY,
//	Admin_Password text NOT NULL CHECK (char_length(Admin_Password) >= 4)
//);

