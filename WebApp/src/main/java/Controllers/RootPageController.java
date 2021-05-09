package Controllers;

import Models.Books;
import Services.BooksService;
import Services.CustomersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RootPageController {
    CustomersService customersService = new CustomersService();
    BooksService bookService = new BooksService();

    @GetMapping("/")
    public String hello(Model model) {
        List<Books> books = bookService.readAllBooks();
        model.addAttribute("books", books);
        return "index";
    }
}
