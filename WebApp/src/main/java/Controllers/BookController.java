package Controllers;

import Models.Admin;
import Models.Books;
import Services.AdminService;
import Services.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    BooksService bookService = new BooksService();
    AdminService adminService = new AdminService();
    @GetMapping("/book")
    public String bookPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username,
                           @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Books book = bookService.readBookByID(book_id);
        model.addAttribute("book", book);

        Admin exist_admin = adminService.readAdminByLogin(username);
        if (exist_admin != null) {
            return "bookAdmin";
        }

        if(username.equals("DefaultValueForCookieUsername")) {
            return "book";
        }

        return "LUbook";
    }

    @GetMapping("/bookEdit")
    public String bookEditPage(@RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Books book = bookService.readBookByID(book_id);
        model.addAttribute("book", book);
        return "bookEdit";
    }

    @PostMapping("/saveEditBook")
    public String saveBookEditPage(@RequestParam(name = "title") String title,
                                   @RequestParam(name = "author") String author,
                                   @RequestParam(name = "genre") String genre,
                                   @RequestParam(name = "pub_house") String pub_house,
                                   @RequestParam(name = "year") int year,
                                   @RequestParam(name = "num_of_pages") int num_of_pages,
                                   @RequestParam(name = "cover_type") String cover_type,
                                   @RequestParam(name = "price") double price,
                                   @RequestParam(name = "amount") int amount,
                                   @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Books book = bookService.readBookByID(book_id);


        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPub_house(pub_house);
        book.setPub_year(year);
        book.setNum_pages(num_of_pages);
        book.setCover_type(cover_type);
        book.setPrice(price);
        book.setAmount(amount);

        bookService.updateBook(book);

        Books check_book = bookService.readBookByID(book.getBook_id());
        if(check_book == null) {
            model.addAttribute("error", "Book update error");
            return "pageERROR";
        }
        model.addAttribute("book", book);
        return "book";
    }


}
