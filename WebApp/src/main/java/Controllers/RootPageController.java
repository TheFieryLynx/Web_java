package Controllers;

import Models.Books;
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

    @GetMapping("/")
    public String hello(Model model, @CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username) {
        System.out.println(username);
        List<Books> books = bookService.readAllBooks();
        model.addAttribute("books", books);
        if (!username.equals("DefaultValueForCookieUsername")) {
            return "redirect:/logged";
        }
        return "index";
    }
}
