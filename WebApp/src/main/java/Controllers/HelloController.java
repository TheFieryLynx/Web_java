package Controllers;

import Models.Books;
import Models.Customers;
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

@Controller
public class HelloController {
    CustomersService customersService = new CustomersService();
    BooksService bookService = new BooksService();

    @PostMapping("/superSecureLogin")
    public String superSecureLoginPage(HttpServletResponse response,
                                        @RequestParam(name="login", required=true) String login,
                                        @RequestParam(name="password", required=true) String password, Model model) {
        Customers customer = customersService.readCustomerByLogin(login);

        if ((customer != null) && (password.equals(customer.getCustomer_password()))) {
            Cookie cookie = new Cookie("login", customer.getCustomer_login());
            response.addCookie(cookie);
            return "redirect:/AuthorizedCustomer";
        }

        return "superSecureLogin";
    }

    @GetMapping("/superSecureLogin")
    public String superSecureLoginPage() {
        return "superSecureLogin";
    }

    @GetMapping("/AuthorizedCustomer")
    public String superSecureAuthorizedCustomer( @CookieValue(value = "login", defaultValue = "nfksnlfdkf") String username,
                                                 @RequestParam(name="login", required=false) String login,
                                                 @RequestParam(name="password", required=false) String password, Model model) {
        Books book = bookService.readBookByID(1);
        System.out.println(username);
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