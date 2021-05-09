package Controllers;

import Models.Books;
import Services.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    BooksService bookService = new BooksService();

    @GetMapping("/book")
    public String filmPage(@RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Books book = bookService.readBookByID(book_id);

        model.addAttribute("book", book);
        return "book";
    }
}
