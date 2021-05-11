package Controllers;

import Models.Admin;
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

@Controller
public class OrderController {
    BooksService bookService = new BooksService();
    CustomersService customersService = new CustomersService();

    @GetMapping("/bookBuy")
    public String bookBuyPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username,
                              @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Books book = bookService.readBookByID(book_id);
        model.addAttribute("book", book);
        Customers customer = customersService.readCustomerByLogin(username);
        model.addAttribute("customer", customer);

        return "bookCheckOrder";
    }

    @GetMapping("/bookBuyEdit")
    public String bookBuyEditPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username,
                              @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Books book = bookService.readBookByID(book_id);
        model.addAttribute("book", book);
        Customers customer = customersService.readCustomerByLogin(username);
        model.addAttribute("customer", customer);

        return "bookEditCheckOrder";
    }

    @PostMapping("/saveBookEditOrder")
    public String saveBookEditOrderPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username,
                                        @RequestParam(name = "first_name") String first_name,
                                        @RequestParam(name = "last_name") String last_name,
                                        @RequestParam(name = "address") String address,
                                        @RequestParam(name = "phone_number") String phone_number,
                                        @RequestParam(name = "mail") String mail,
                                        @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Books book = bookService.readBookByID(book_id);
        model.addAttribute("book", book);
        Customers customer = customersService.readCustomerByLogin(username);
        customer.setFirstname(first_name);
        customer.setLastname(last_name);
        customer.setAddress(address);
        customer.setPhone_number(phone_number);
        customer.setMail(mail);

        model.addAttribute("customer", customer);



        return "bookCheckOrder";
    }

    @PostMapping("/bookMakingOrder")
    public String savebookMakingOrderPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username,
                                        @RequestParam(name = "customer_changed_lastName") String last_name,
                                        @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        System.out.println(last_name);
        System.out.println("!!!!!!!!!!!!!");

        Books book = bookService.readBookByID(book_id);
        model.addAttribute("book", book);

        return "LUbook";
    }
}

