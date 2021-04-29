package Controllers;

import Models.Books;
import Models.Customers;
import Services.BooksService;
import Services.CustomersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class HelloController {
    CustomersService customersService = new CustomersService();
    BooksService bookService = new BooksService();
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @PostMapping("/superSecureLogin")
    public String superSecureLoginPage(@RequestParam(name="login", required=true) String login,
                                       @RequestParam(name="password", required=true) String password, Model model) {
        Customers customer = customersService.readCustomerByLogin(login);
        if ((customer != null) && (password.equals(customer.getCustomer_password()))) {
            return "redirect:/AuthorizedCustomer";
        }

        return "superSecureLogin";
    }

    @GetMapping("/superSecureLogin")
    public String superSecureLoginPage() {
        return "superSecureLogin";
    }

    @GetMapping("/AuthorizedCustomer")
    public String superSecureAuthorizedCustomer(
                                                 @RequestParam(name="login", required=false) String login,
                                                 @RequestParam(name="password", required=false) String password, Model model) {
        Books book = bookService.readBookByID(1);

        model.addAttribute("login", login);
        model.addAttribute("password", password);

        model.addAttribute("book_id", book.getBook_id());
        model.addAttribute("price", book.getPrice());
        model.addAttribute("amount", book.getAmount());
        model.addAttribute("title", book.getTitle());
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("genre", book.getGenre());
        model.addAttribute("pub_house", book.getPub_house());
        model.addAttribute("pub_year", book.getPub_year());
        model.addAttribute("num_pages", book.getNum_pages());
        model.addAttribute("cover_type", book.getCover_type());
        return "AuthorizedCustomer";
    }

//    @GetMapping("/AuthorizedCustomer")
//    public String superAuthorizedCustomerPage() {
//        return "AuthorizedCustomer";
//    }


}