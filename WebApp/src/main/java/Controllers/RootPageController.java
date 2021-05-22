package Controllers;

import Models.Admin;
import Models.Books;
import Models.Customers;
import Services.AdminService;
import Services.BooksService;
import Services.CustomersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import java.util.List;

@Controller
public class RootPageController {
    CustomersService customersService = new CustomersService();
    BooksService bookService = new BooksService();
    AdminService adminService = new AdminService();

    @GetMapping("/")
    public String hello(Model model, @CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                     @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password) {
        List<Books> books = bookService.readAllBooks();
        model.addAttribute("books", books);

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                return "redirect:/logged";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                return "redirect:/logged";
            }
        }

        return "index";
    }
}
