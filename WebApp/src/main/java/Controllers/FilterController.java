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

import java.util.List;

@Controller
public class FilterController {
    BooksService bookService = new BooksService();
    CustomersService customersService = new CustomersService();
    AdminService adminService = new AdminService();

    @PostMapping("/filter")
    public String bookPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                           @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "author") String author,
                           @RequestParam(name = "genre") String genre,
                           Model model) {

        List<Books> titles = bookService.readAllBooks();
        List<Books> authors = bookService.readAllBooks();
        List<Books> genres = bookService.readAllBooks();

        if (title != "") {
            titles = bookService.readBooksListByTitle(title);
        }

        if (author != "") {
            authors = bookService.readBooksListByAuthor(author);
        }

        if (genre != "") {
            genres = bookService.readBooksListByGenre(genre);
        }

        titles.retainAll(authors);
        titles.retainAll(genres);

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                model.addAttribute("books", titles);
                return "LAindex";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                model.addAttribute("books", titles);
                model.addAttribute("customer_id", exist_customer.getCustomer_id());
                return "LUindex";
            }
        }

        model.addAttribute("books", titles);
        return "index";
    }
}
