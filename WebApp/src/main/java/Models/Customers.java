package Models;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customers
{
    public Customers(String customer_login,
                        String customer_password, String lastname,
                            String firstname, String address, String phone_number,
                                String mail, boolean deleted_account) {
        this.customer_login = customer_login;
        this.customer_password = customer_password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.phone_number = phone_number;
        this.mail = mail;
        this.deleted_account = deleted_account;
    }

    public Customers() {    }

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_login() {
        return customer_login;
    }

    public void setCustomer_login(String customer_login) {
        this.customer_login = customer_login;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean getDeleted_account() {
        return deleted_account;
    }

    public void setDeleted_account(boolean deleted_account) {
        this.deleted_account = deleted_account;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Customers other = (Customers) obj;
        return (this.customer_id == other.customer_id) &&
                (this.customer_login.equals(other.customer_login)) &&
                (this.customer_password.equals(other.customer_password)) &&
                (this.lastname.equals(other.lastname)) &&
                (this.firstname.equals(other.firstname)) &&
                (this.address.equals(other.address)) &&
                (this.phone_number.equals(other.phone_number)) &&
                (this.mail.equals(other.mail));
    }

    private int customer_id;
    private String customer_login;
    private String customer_password;
    private String lastname;
    private String firstname;
    private String address;
    private String phone_number;
    private String mail;
    private boolean deleted_account;

}
