package Controllers;

import Models.Admin;
import Models.Books;
import Models.Customers;
import Models.Orders;
import Services.BooksService;
import Services.CustomersService;
import Services.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Controller
public class OrderController {
    BooksService bookService = new BooksService();
    CustomersService customersService = new CustomersService();
    OrdersService ordersService = new OrdersService();

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
                                          @RequestParam(name = "customer_changed_firstName") String first_name,
                                          @RequestParam(name = "customer_changed_lastName") String last_name,
                                          @RequestParam(name = "customer_changed_address") String address,
                                          @RequestParam(name = "customer_changed_phoneNumber") String phone_number,
                                          @RequestParam(name = "customer_changed_mail") String mail,
                                        @RequestParam(name = "book_id", required = true) int book_id, Model model) throws ParseException {
        OrdersService ordersService = new OrdersService();

        Books book = bookService.readBookByID(book_id);
        model.addAttribute("book", book);

        Customers customer = customersService.readCustomerByLogin(username);
        customer.setFirstname(first_name);
        customer.setLastname(last_name);
        customer.setAddress(address);
        customer.setPhone_number(phone_number);
        customer.setMail(mail);

        LocalDate current_date = LocalDate.now();
        String dt = current_date.toString();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, 5);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date

        System.out.println(current_date);
        System.out.println(dt);
        Orders new_order = new Orders(customer, address,
                java.sql.Date.valueOf(current_date), "processing", java.sql.Date.valueOf("2020-02-16"), book.getPrice(),
                book);

        ordersService.createOrder(new_order);

        model.addAttribute("time", dt);

        return "successOrder";
    }

    @GetMapping("/logged/orders")
    public String orderCustomerPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username,
                            @RequestParam(name = "customer_id", required = true) int customer_id, Model model) {
        Customers customer = customersService.readCustomerByID(customer_id);
        List<Orders> orders = ordersService.readOrdersByCustomerId(customer);
        model.addAttribute("orders", orders);
        return "customerOrders";
    }

    @GetMapping("/orders")
    public String ordersPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String username, Model model) {
        List<Orders> orders = ordersService.readOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/orderEdit")
    public String customerEditPage(@RequestParam(name = "order_id", required = true) int order_id, Model model) {
        Orders order = ordersService.readOrderByID(order_id);
        model.addAttribute("order", order);
        return "orderEdit";
    }

    @PostMapping("/saveEditOrder")
    public String saveCustomerEditPage(@RequestParam(name = "address") String address,
                                       @RequestParam(name = "delivery_time") java.sql.Date delivery_time,
                                       @RequestParam(name = "status") String status,
                                       @RequestParam(name = "order_time") java.sql.Date order_time,
                                       @RequestParam(name = "order_price") double order_price,
                                       @RequestParam(name = "order_id", required = true) int order_id, Model model) {

        Orders order = ordersService.readOrderByID(order_id);
        order.setAddress(address);
        order.setDelivery_time(delivery_time);
        order.setStatus(status);
        order.setOrder_time(order_time);
        order.setOrder_price(order_price);

        ordersService.updateOrder(order);

        Orders check_order = ordersService.readOrderByID(order.getOrder_id());
        if (check_order == null) {
            model.addAttribute("error", "Order update error");
            return "pageERROR";
        }
        List<Orders> orders = ordersService.readOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }



}

