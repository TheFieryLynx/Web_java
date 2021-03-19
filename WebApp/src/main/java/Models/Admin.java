package Models;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin
{
    public Admin(String admin_login, String admin_password) {
        this.admin_login = admin_login;
        this.admin_password = admin_password;
    }

    public Admin() {    }

    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_login() {
        return admin_login;
    }

    public void setAdmin_login(String admin_login) {
        this.admin_login = admin_login;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Admin other = (Admin) obj;
        return (this.admin_id == other.admin_id) &&
                (this.admin_login.equals(other.admin_login)) &&
                (this.admin_password.equals(other.admin_password));
    }

    private int admin_id;
    private String admin_login;
    private String admin_password;
}

