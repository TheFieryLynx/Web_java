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
    public String hello(Model model, @CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username) {
        List<Books> books = bookService.readAllBooks();
        model.addAttribute("books", books);

        Admin exist_admin = adminService.readAdminByLogin(username);
        if (exist_admin != null) {
            return "LAindex";
        }

        Customers exist_customer = customersService.readCustomerByLogin(username);
        model.addAttribute("customer_id", exist_customer.getCustomer_id());
        return "LUindex";
    }

    @GetMapping("/logIn")
    public String registrationPage() {
        return "logIn";
    }

    @GetMapping("/logOut")
    public String logOut(HttpServletResponse response, @CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username) {
        System.out.println(username);
        Cookie cookie = new Cookie("login", "DefaultValueForCookieUsername");
        response.addCookie(cookie);
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
                Cookie cookie = new Cookie("login", username);
                response.addCookie(cookie);
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
                Cookie cookie = new Cookie("login", username);
                response.addCookie(cookie);
                model.addAttribute("admin_id", exist_admin.getAdmin_id());
                return "LAindex";
            }
        }

        model.addAttribute("error", "Username or password is incorrect.");
        return "pageERROR";
    }

}
