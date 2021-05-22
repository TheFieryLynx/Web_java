package Controllers;

import Models.Admin;
import Models.Books;
import Models.Customers;
import Models.Orders;
import Services.AdminService;
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
    AdminService adminService = new AdminService();

    @GetMapping("/bookBuy")
    public String bookBuyPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                              @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                              @RequestParam(name = "book_id", required = true) int book_id, Model model) {

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Books book = bookService.readBookByID(book_id);
                model.addAttribute("book", book);
                Customers customer = customersService.readCustomerByLogin(cookie_username);
                model.addAttribute("customer", customer);
                return "bookCheckOrder";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                Books book = bookService.readBookByID(book_id);
                model.addAttribute("book", book);
                Customers customer = customersService.readCustomerByLogin(cookie_username);
                model.addAttribute("customer", customer);
                return "bookCheckOrder";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @GetMapping("/bookBuyEdit")
    public String bookBuyEditPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                  @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                  @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Books book = bookService.readBookByID(book_id);
                model.addAttribute("book", book);
                Customers customer = customersService.readCustomerByLogin(cookie_username);
                model.addAttribute("customer", customer);

                return "bookEditCheckOrder";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                Books book = bookService.readBookByID(book_id);
                model.addAttribute("book", book);
                Customers customer = customersService.readCustomerByLogin(cookie_username);
                model.addAttribute("customer", customer);

                return "bookEditCheckOrder";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @PostMapping("/saveBookEditOrder")
    public String saveBookEditOrderPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                        @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                        @RequestParam(name = "first_name") String first_name,
                                        @RequestParam(name = "last_name") String last_name,
                                        @RequestParam(name = "address") String address,
                                        @RequestParam(name = "phone_number") String phone_number,
                                        @RequestParam(name = "mail") String mail,
                                        @RequestParam(name = "book_id", required = true) int book_id, Model model) {
        System.out.println(cookie_username);
        System.out.println(cookie_password);
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Books book = bookService.readBookByID(book_id);
                model.addAttribute("book", book);
                Customers customer = customersService.readCustomerByLogin(cookie_username);
                customer.setFirstname(first_name);
                customer.setLastname(last_name);
                customer.setAddress(address);
                customer.setPhone_number(phone_number);
                customer.setMail(mail);

                model.addAttribute("customer", customer);

                return "bookCheckOrder";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        System.out.println(exist_customer.getCustomer_login());
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                Books book = bookService.readBookByID(book_id);
                model.addAttribute("book", book);
                Customers customer = customersService.readCustomerByLogin(cookie_username);
                customer.setFirstname(first_name);
                customer.setLastname(last_name);
                customer.setAddress(address);
                customer.setPhone_number(phone_number);
                customer.setMail(mail);

                model.addAttribute("customer", customer);
                return "bookCheckOrder";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";


    }

    @PostMapping("/bookMakingOrder")
    public String saveBookMakingOrderPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                          @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                          @RequestParam(name = "customer_changed_firstName") String first_name,
                                          @RequestParam(name = "customer_changed_lastName") String last_name,
                                          @RequestParam(name = "customer_changed_address") String address,
                                          @RequestParam(name = "customer_changed_phoneNumber") String phone_number,
                                          @RequestParam(name = "customer_changed_mail") String mail,
                                          @RequestParam(name = "book_id", required = true) int book_id, Model model) throws ParseException {

        OrdersService ordersService = new OrdersService();

        Books book = bookService.readBookByID(book_id);
        if (book.getAmount() == 0) {
            model.addAttribute("error", "Product is out of stock");
            return "pageERROR";
        }
        book.setAmount(book.getAmount() - 1);
        bookService.updateBook(book);
        //model.addAttribute("book", book);

        LocalDate current_date = LocalDate.now();
        String dt = current_date.toString();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, 5);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Customers customer = customersService.readCustomerByLogin(cookie_username);
                customer.setFirstname(first_name);
                customer.setLastname(last_name);
                customer.setAddress(address);
                customer.setPhone_number(phone_number);
                customer.setMail(mail);

                Orders new_order = new Orders(customer, address,
                        java.sql.Date.valueOf(current_date), "processing", java.sql.Date.valueOf("2020-02-16"), book.getPrice(),
                        book);

                ordersService.createOrder(new_order);
                model.addAttribute("time", dt);
                return "successOrder";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                Customers customer = customersService.readCustomerByLogin(cookie_username);
                customer.setFirstname(first_name);
                customer.setLastname(last_name);
                customer.setAddress(address);
                customer.setPhone_number(phone_number);
                customer.setMail(mail);

                Orders new_order = new Orders(customer, address,
                        java.sql.Date.valueOf(current_date), "processing", java.sql.Date.valueOf("2020-02-16"), book.getPrice(),
                        book);

                ordersService.createOrder(new_order);
                model.addAttribute("time", dt);
                return "successOrder";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @GetMapping("/logged/orders")
    public String orderCustomerPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                    @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                    @RequestParam(name = "customer_id", required = true) int customer_id, Model model) {

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Customers customer = customersService.readCustomerByID(customer_id);
                List<Orders> orders = ordersService.readOrdersByCustomerId(customer);
                model.addAttribute("orders", orders);
                return "customerOrders";
            }
        }

        Customers exist_customer = customersService.readCustomerByLogin(cookie_username);
        if (exist_customer != null) {
            if (exist_customer.getCustomer_password().equals(cookie_password)) {
                Customers customer = customersService.readCustomerByID(customer_id);
                List<Orders> orders = ordersService.readOrdersByCustomerId(customer);
                model.addAttribute("orders", orders);
                return "customerOrders";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";


    }

    @GetMapping("/orders")
    public String ordersPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                             @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                             Model model) {

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                List<Orders> orders = ordersService.readOrders();
                model.addAttribute("orders", orders);
                return "orders";
            }
        }

        model.addAttribute("error", "Permission denied");
        return "pageERROR";


    }

    @GetMapping("/orderEdit")
    public String customerEditPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                   @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                   @RequestParam(name = "order_id", required = true) int order_id, Model model) {
        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
                Orders order = ordersService.readOrderByID(order_id);
                model.addAttribute("order", order);
                return "orderEdit";
            }
        }
        model.addAttribute("error", "Permission denied");
        return "pageERROR";
    }

    @PostMapping("/saveEditOrder")
    public String saveCustomerEditPage(@CookieValue(value = "login", defaultValue = "DefaultValueForCookieUsername") String cookie_username,
                                       @CookieValue(value = "password", defaultValue = "DefaultValueForCookiePassword") String cookie_password,
                                       @RequestParam(name = "address") String address,
                                       @RequestParam(name = "delivery_time") java.sql.Date delivery_time,
                                       @RequestParam(name = "status") String status,
                                       @RequestParam(name = "order_time") java.sql.Date order_time,
                                       @RequestParam(name = "order_price") double order_price,
                                       @RequestParam(name = "order_id", required = true) int order_id, Model model) {

        Admin exist_admin = adminService.readAdminByLogin(cookie_username);
        if (exist_admin != null) {
            if (exist_admin.getAdmin_password().equals(cookie_password)) {
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

        model.addAttribute("error", "Permission denied");
        return "pageERROR";


    }



}

