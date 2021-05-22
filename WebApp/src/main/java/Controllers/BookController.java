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

import java.awt.print.Book;
import java.util.List;

@Controller
public class BookController {
    BooksService bookService = new BooksService();
    AdminService adminService = new AdminService();
    CustomersService customersService = new CustomersService();

    @GetMapping("/book")
    public String bookPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                           @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                           @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Books book = bookService.readBookByID(book_id);
        model.addAttribute("book", book);

        if (book == null) {
            model.addAttribute("error", "This book is no longer for sale");
            return "pageERROR";
        }

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                return "bookAdmin";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                return "LUbook";
            }
        }
        return "book";
    }

    @GetMapping("/bookEdit")
    public String bookEditPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                               @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                               @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Books book = bookService.readBookByID(book_id);
                model.addAttribute("book", book);
                return "bookEdit";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";

    }

    @PostMapping("/saveEditBook")
    public String saveBookEditPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                   @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                   @RequestParam(name = "title") String title,
                                   @RequestParam(name = "author") String author,
                                   @RequestParam(name = "genre") String genre,
                                   @RequestParam(name = "pub_house") String pub_house,
                                   @RequestParam(name = "year") int year,
                                   @RequestParam(name = "num_of_pages") int num_of_pages,
                                   @RequestParam(name = "cover_type") String cover_type,
                                   @RequestParam(name = "price") double price,
                                   @RequestParam(name = "amount") int amount,
                                   @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
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
                return "bookAdmin";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @GetMapping("/deleteBook")
    public String bookDeletePage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                 @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Books book = bookService.readBookByID(book_id);
                book.setDeleted_book(true);
                bookService.updateBook(book);
                List<Books> books = bookService.readAllBooks();
                model.addAttribute("books", books);
                return "LAindex";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @GetMapping("/addBook")
    public String addBookPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                              @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password, Model model) {
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                return "bookAdd";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";

    }

    @PostMapping("/bookCreation")
    public String checkRegistrationData (@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                         @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                         @RequestParam(name = "title") String title,
                                         @RequestParam(name = "author") String author,
                                         @RequestParam(name = "genre") String genre,
                                         @RequestParam(name = "pub_house") String pub_house,
                                         @RequestParam(name = "year") int year,
                                         @RequestParam(name = "num_of_pages") int num_of_pages,
                                         @RequestParam(name = "cover_type") String cover_type,
                                         @RequestParam(name = "price") double price,
                                         @RequestParam(name = "amount") int amount,
                                         Model model) {

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Books book = new Books(price, amount, title, author, genre,
                        pub_house, year, num_of_pages, cover_type, false);
                bookService.createBook(book);
                List<Books> books = bookService.readAllBooks();
                model.addAttribute("books", books);
                return "LAindex";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";


    }
}
