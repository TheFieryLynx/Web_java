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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LogInController {
    CustomersService customersService = new CustomersService();
    AdminService adminService = new AdminService();
    BooksService bookService = new BooksService();

    @GetMapping("/logged")
    public String hello(Model model, @CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                     @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password) {
        List<Books> books = bookService.readAllBooks();
        model.addAttribute("books", books);

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                return "LAindex";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                model.addAttribute("customer_id", exist_customer.getCustomer_id());
                return "LUindex";
            }
        }

        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @GetMapping("/logIn")
    public String registrationPage(Model model, @CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username) {
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            model.addAttribute("error", "Permission denied");
            return "pageERROR";
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            model.addAttribute("error", "Permission denied");
            return "pageERROR";
        }
        return "logIn";
    }

    @GetMapping("/logOut")
    public String logOut(HttpServletResponse response) {
        Cookie cookie_username = new Cookie("login", "DefaultValueForCookieUsername");
        Cookie cookie_password = new Cookie("password", "DefaultValueForCookiePassword");
        response.addCookie(cookie_username);
        response.addCookie(cookie_password);
        return "redirect:/";
    }

    @PostMapping("/identification")
    public String checkRegistrationData (HttpServletResponse response,
                                         @RequestParam(name = "username") String username,
                                         @RequestParam(name = "password") String password,
                                         Model model) {

        Customers exist_customer = customersService.readCustomerByLogin(username);
        Admin exist_admin = adminService.readAdminByLogin(username);

        if (exist_customer != null) {
            if (!exist_customer.getCustomer_password().equals(password)) {
                model.addAttribute("error", "Username or password is incorrect.");
                return "pageERROR";
            } else {
                List<Books> books = bookService.readAllBooks();
                model.addAttribute("books", books);

                Cookie cookie_username = new Cookie("login", username);
                Cookie cookie_password = new Cookie("password", password);
                response.addCookie(cookie_username);
                response.addCookie(cookie_password);

                model.addAttribute("customer_id", exist_customer.getCustomer_id());
                return "LUindex";
            }
        }


        if (exist_admin != null) {
            if (!exist_admin.getAdmin_password().equals(password)) {
                model.addAttribute("error", "Username or password is incorrect.");
                return "pageERROR";
            } else {
                List<Books> books = bookService.readAllBooks();
                model.addAttribute("books", books);

                Cookie cookie_username = new Cookie("login", username);
                Cookie cookie_password = new Cookie("password", password);
                response.addCookie(cookie_username);
                response.addCookie(cookie_password);

                model.addAttribute("admin_id", exist_admin.getAdmin_id());
                return "LAindex";
            }
        }

        model.addAttribute("error", "Username or password is incorrect.");
        return "pageERROR";
    }

}
