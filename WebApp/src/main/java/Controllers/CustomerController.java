package Controllers;

import Models.Admin;
import Models.Books;
import Models.Customers;
import Models.Orders;
import Services.AdminService;
import Services.BooksService;
import Services.CustomersService;
import Services.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerController {
    CustomersService customersService = new CustomersService();
    AdminService adminService = new AdminService();
    BooksService bookService = new BooksService();

    @GetMapping("/logged/personal")
    public String customerPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                               @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                               @RequestParam(name = "customer_id", required = true) int customer_id, Model model) {

        Customers customer = customersService.readCustomerByID(customer_id);

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                model.addAttribute("customer", customer);
                return "customerAdmin";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                model.addAttribute("customer", customer);
                return "customer";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @GetMapping("/customerEdit")
    public String customerEditPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                   @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                   @RequestParam(name = "customer_id", required = true) int customer_id, Model model) {
        Customers customer = customersService.readCustomerByID(customer_id);
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                model.addAttribute("customer", customer);
                return "customerEdit";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                model.addAttribute("customer", customer);
                return "customerEdit";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @GetMapping("/customerDelete")
    public String customerDeletePage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                     @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                     @RequestParam(name = "customer_id", required = true) int customer_id, Model model) {
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Customers customer = customersService.readCustomerByID(customer_id);
                if (customer != null) {
                    customer.setDeleted_account(true);
                    customersService.updateCustomer(customer);
                }
                List<Customers> customers = customersService.readCustomers();
                model.addAttribute("customers", customers);
                return "customers";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @PostMapping("/saveEditCustomer")
    public String saveCustomerEditPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                       @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                       @RequestParam(name = "password") String password,
                                       @RequestParam(name = "first_name") String first_name,
                                       @RequestParam(name = "last_name") String last_name,
                                       @RequestParam(name = "address") String address,
                                       @RequestParam(name = "phone_number") String phone_number,
                                       @RequestParam(name = "mail") String mail,
                                       @RequestParam(name = "customer_id", required = true) int customer_id, Model model) {

        Customers customer = customersService.readCustomerByID(customer_id);
        customer.setCustomer_password(password);
        customer.setFirstname(first_name);
        customer.setLastname(last_name);
        customer.setAddress(address);
        customer.setPhone_number(phone_number);
        customer.setMail(mail);

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {

                customersService.updateCustomer(customer);
                Customers check_customer = customersService.readCustomerByID(customer.getCustomer_id());
                if (check_customer == null) {
                    model.addAttribute("error", "Customer update error");
                    return "pageERROR";
                }
                model.addAttribute("customer", customer);
                return "customerAdmin";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {

                customersService.updateCustomer(customer);
                Customers check_customer = customersService.readCustomerByID(customer.getCustomer_id());
                if (check_customer == null) {
                    model.addAttribute("error", "Customer update error");
                    return "pageERROR";
                }
                model.addAttribute("customer", customer);
                return "customer";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";


    }

    @GetMapping("/customers")
    public String ordersPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                             @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                             Model model) {

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                List<Customers> customers = customersService.readCustomers();
                model.addAttribute("customers", customers);
                return "customers";
            }
        }

        model.addAttribute("error", "Permission denied");
        return "pageERROR";

    }

    @GetMapping("/addCustomer")
    public String addBookPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                              @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                              Model model) {
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                return "customerAdd";
            }
        }

        model.addAttribute("error", "Permission denied");
        return "pageERROR";

    }

    @PostMapping("/customerCreation")
    public String checkRegistrationData (@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                         @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                         @RequestParam(name = "username") String username,
                                         @RequestParam(name = "password") String password,
                                         @RequestParam(name = "first_name") String first_name,
                                         @RequestParam(name = "last_name") String last_name,
                                         @RequestParam(name = "address") String address,
                                         @RequestParam(name = "phone_number") String phone_number,
                                         @RequestParam(name = "mail") String mail,
                                         Model model) {
        Customers new_customer;
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Customers exist_customer = customersService.readCustomerByLogin(username);
                if (exist_customer != null) {
                    model.addAttribute("error", "This username is already taken! :(");
                    return "pageERROR";
                }

                try {
                    new_customer = new Customers(username, password,
                            last_name, first_name, address, phone_number, mail, false);
                    customersService.createCustomer(new_customer);

                } catch (Exception e) {
                    model.addAttribute("error", e.getMessage());
                    return "pageERROR";
                }

                Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
                if (check_customer == null) {
                    model.addAttribute("error", "Account creation error");
                    return "pageERROR";
                }
                List<Books> books = bookService.readAllBooks();
                model.addAttribute("books", books);
                return "LAindex";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }
}